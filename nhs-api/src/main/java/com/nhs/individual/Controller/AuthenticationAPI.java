package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Service.UserService;
import com.nhs.individual.Utils.IUserDetail;
import com.nhs.individual.Utils.NewJwtProvider;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.nhs.individual.Utils.Constant.AUTH_TOKEN;
import static com.nhs.individual.Utils.Constant.AUTH_TOKEN_COOKIE_MAXAGE;

@RestController
public class AuthenticationAPI {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    NewJwtProvider jwtProvider;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account account){
        return accountService.create(account);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> login(@RequestBody Account account){
        Authentication authentication=new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword());
        Authentication auth = null;
        auth=authenticationManager.authenticate(authentication);
        Cookie cookie=new Cookie(AUTH_TOKEN,jwtProvider.generateToken(IUserDetail.parseFrom(account)));
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) AUTH_TOKEN_COOKIE_MAXAGE);
        return ResponseEntity.ok()
                .header("set-cookie", cookie.getValue())
                .body(ResponseMessage.builder().message("Login success").ok());
    }
    @RequestMapping(value = "/testauth", method = RequestMethod.GET)
    public String testAuth(){
        return "testauth ok";
    }
}
