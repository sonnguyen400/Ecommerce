package com.nhs.individual.utils;

import com.nhs.individual.secure.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Optional;

@Component
public class RequestUtils {
    @Autowired
    private JwtProvider jwtProvider;

    public Claims extractJwtClaimFromCookie(HttpServletRequest request, String cookieName)  {
        Cookie cookie= WebUtils.getCookie(request,cookieName);
        if(cookie != null){
            return jwtProvider.validate(cookie.getValue());
        }
        return null;
    }
    public Cookie getExpiredCookie(String cookie){
        Cookie expiredCookie=new Cookie(cookie,"");
        expiredCookie.setMaxAge(0);
        return expiredCookie;
    }
    public static Optional<Cookie> getCookie(HttpServletRequest request, String cookieName){
        return Optional.ofNullable(WebUtils.getCookie(request, cookieName));
    }
}
