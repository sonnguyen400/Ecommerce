package com.nhs.individual.Security.Oauth2;

import com.nhs.individual.Service.AuthService;
import com.nhs.individual.Utils.IUserDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        IUserDetail account=(IUserDetail)authentication.getPrincipal();
        System.out.println(account.getUsername());
        response.addHeader(HttpHeaders.SET_COOKIE,authService.accessTokenCookie(account.getUsername()).toString());
        response.addHeader(HttpHeaders.SET_COOKIE,authService.refreshTokenCookie(account.getId()).toString());
        response.sendRedirect("http://localhost:3000");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

