package com.nhs.individual.Utils;

import com.nhs.individual.Domain.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class IUserDetail implements UserDetails,OAuth2User {
    private Integer id;
    private Integer userId;
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    private OAuth2User oAuth2User;

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    public IUserDetail(OAuth2User oAuth2User){
        this.oAuth2User=oAuth2User;
        this.username=oAuth2User.getName();
        this.authorities=oAuth2User.getAuthorities().stream().map(d->new SimpleGrantedAuthority(d.getAuthority())).toList();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> gran=new ArrayList<>();
        gran.add(new SimpleGrantedAuthority("ROLE_USER"));
        return gran;
    }
    public IUserDetail(){

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }


    public  IUserDetail(Account account){
        this.username=account.getUsername();
        this.id=account.getId();
        this.password=account.getPassword();
        this.userId=account.getUser().getId();
        if(account.getRoles() != null&&account.getRoles().size()>0){
            this.setAuthorities(account.getRoles().stream().map(e->new SimpleGrantedAuthority(e.getName())).toList());
        }
    }


    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}
