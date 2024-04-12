package com.nhs.individual.Config;

import com.nhs.individual.Service.RefreshTokenService;
import com.nhs.individual.Utils.RequestUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import static com.nhs.individual.Utils.Constant.AUTH_TOKEN;
import static com.nhs.individual.Utils.Constant.REFRESH_AUTH_TOKEN;

@Component
public class LogoutHandlerCustomize implements LogoutHandler {
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    RequestUtils requestUtils;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cookie cookie = WebUtils.getCookie(request, REFRESH_AUTH_TOKEN);
        if(cookie!=null&&cookie.getValue()!=null){
            refreshTokenService
                    .findByToken(cookie.getValue())
                    .ifPresent(token->{
                        refreshTokenService.deleteById(token.getId());
                    });
        }
        response.addCookie(requestUtils.getExpiredCookie(REFRESH_AUTH_TOKEN));
        response.addCookie(requestUtils.getExpiredCookie(AUTH_TOKEN));
        SecurityContextHolder.clearContext();
    }
}
