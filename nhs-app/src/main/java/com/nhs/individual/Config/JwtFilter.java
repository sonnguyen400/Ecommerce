package com.nhs.individual.Config;

import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Utils.IUserDetail;
import com.nhs.individual.Utils.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private AccountService service;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=extractTokenFromCookie(request);
        if(token==null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }else{
            service.findByUsername(jwtProvider.getSubject(token))
                    .map(IUserDetail::parseFrom)
                    .ifPresent(user->{
                        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    });
        }
        filterChain.doFilter(request,response);
    }
    private String extractTokenFromCookie(HttpServletRequest request){
        Cookie cookie= WebUtils.getCookie(request,"jwtToken");
        return Optional.ofNullable(cookie)
                .map((ck->jwtProvider.validate(ck.getValue())))
                .orElse(null);
    }
}
