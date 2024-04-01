package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.User;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import com.nhs.individual.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationAPI {
    @Autowired
    AuthService authService;

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
    @RequestMapping(value = "/auth/user",method = RequestMethod.GET)
    public Account getcurrentAccount(){
        return authService.getAuthenticatedAccount();
    }
}
