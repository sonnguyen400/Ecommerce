package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.User;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import com.nhs.individual.Service.AuthService;
import com.nhs.individual.Service.UserService;
import com.nhs.individual.Utils.IUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationAPI {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account account){
        return authService.register(account);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> login(@RequestBody Account account){
        return authService.signIn(account);
    }
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> refreshToken(HttpServletRequest request){
        return authService.refresh(request);
    }
    @RequestMapping(value = "/testauth", method = RequestMethod.GET)
    public String testAuth(){
        return "testauth ok";
    }
    @RequestMapping(value = "/auth/account",method = RequestMethod.GET)
    public IUserDetail getcurrentAccount(){
        return authService.getCurrentAccount();
    }
    @RequestMapping(value = "/auth/user",method = RequestMethod.GET)
    public User getcurrentUser(){
        return userService.findByAccountId(authService.getCurrentAccount().getId());
    }

}
