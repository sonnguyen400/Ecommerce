package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User account){
        return accountService.insert(account);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Account account){
        Authentication authentication=new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword());
        Authentication auth = null;
        try{
            auth=authenticationManager.authenticate(authentication);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("dh");
    }
    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }
}
