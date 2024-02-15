package com.nhs.individual.Utils;
import com.nhs.individual.Exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
@Component
public class RequestUtils {
    @Autowired
    private NewJwtProvider jwtProvider;

    public Claims extractJwtClaimFromCookie(HttpServletRequest request, String cookieName) throws InvalidTokenException, ExpiredJwtException {
        Cookie cookie= WebUtils.getCookie(request,cookieName);
        if(cookie != null){
            return jwtProvider.validate(cookie.getValue());
        }else{
            throw new IllegalArgumentException("Cookie with name " +cookieName+"not found");
        }
    }
}
