package com.nhs.individual.controller;

import com.nhs.individual.domain.Account;
import com.nhs.individual.domain.User;
import com.nhs.individual.responsemessage.ResponseMessage;
import com.nhs.individual.secure.CurrentUser;
import com.nhs.individual.secure.IUserDetail;
import com.nhs.individual.service.AuthService;
import com.nhs.individual.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationAPI {
    AuthService authService;
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
    @RequestMapping(value = "/api/v1/auth/account",method = RequestMethod.GET)
    public IUserDetail getcurrentAccount(){
        return authService.getCurrentAccount();
    }
    @RequestMapping(value = "/api/v1/auth/user",method = RequestMethod.GET)
    public User getcurrentUser(@CurrentUser IUserDetail userDetail){
        return userService.findById(userDetail.getUserId()).get();
    }

}
