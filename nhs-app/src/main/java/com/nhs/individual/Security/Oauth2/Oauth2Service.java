package com.nhs.individual.Security.Oauth2;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.Role;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Service.AuthService;
import com.nhs.individual.Utils.IUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
public class Oauth2Service extends DefaultOAuth2UserService {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthService authService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        IUserDetail oAuth2User=new IUserDetail(super.loadUser(userRequest));
        Account account_=accountService.findByUsername(oAuth2User.getName()).orElseGet(()->{
            //Setup new account
            Account account=new Account();
            account.setUsername(oAuth2User.getName());
            account.setPassword(UUID.randomUUID().toString());
            Role role=new Role();
            role.setId(1);
            account.setRoles(List.of(role));
            //Setup new user
            User user=new User();
            user.setEmail((String) oAuth2User.getAttributes().get("email"));
            user.setFirstname((String) oAuth2User.getAttributes().get("given_name"));
            user.setLastname((String) oAuth2User.getAttributes().get("family_name"));
            user.setPicture((String) oAuth2User.getAttributes().get("picture"));
            account.setUser(user);
            user.setAccount(account);
            return authService.register(account);
        });
        oAuth2User.setId(account_.getId());
        oAuth2User.setUserId(account_.getUser().getId());
        return oAuth2User;
    }


}
