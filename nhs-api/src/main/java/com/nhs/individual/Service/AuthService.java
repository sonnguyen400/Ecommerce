package com.nhs.individual.Service;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import com.nhs.individual.Utils.NewJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.nhs.individual.Utils.Constant.*;

@Service
public class AuthService {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    NewJwtProvider jwtProvider;

    public ResponseEntity<ResponseMessage> signIn(Account account){
        Authentication authentication=new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword());
        Authentication auth = null;
        auth=authenticationManager.authenticate(authentication);
        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,getAuthCookie(account.getUsername()).toString());
        headers.add(HttpHeaders.SET_COOKIE,getRefreshCookie(account.getUsername()).toString());
        return ResponseEntity.ok()
                .headers(headers)
                .body(ResponseMessage.builder().message("Login success").ok());
    }
    public Account register(Account account){
        return accountService.create(account);
    }
    public ResponseCookie getAuthCookie(String name,String value,int maxAge){
         return ResponseCookie.from(name,value)
                .secure(true)
                .httpOnly(true)
                .maxAge((int) REFRESH_TOKEN_AGE)
                .build();
    }
    public ResponseCookie getAuthCookie(String subject){
        return ResponseCookie.from(AUTH_TOKEN,jwtProvider.generateToken(subject))
                .secure(true)
                .httpOnly(true)
                .maxAge((int) REFRESH_TOKEN_AGE)
                .build();
    }
    public ResponseCookie getRefreshCookie(String subject){
        return ResponseCookie.from(REFRESH_AUTH_TOKEN,jwtProvider.generateRefreshToken(new HashMap<>(),subject))
              .secure(true)
              .httpOnly(true)
              .maxAge((int) REFRESH_TOKEN_AGE)
              .build();
    }

}
