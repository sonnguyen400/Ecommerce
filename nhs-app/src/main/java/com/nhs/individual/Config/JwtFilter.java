package com.nhs.individual.Config;

import com.nhs.individual.Exception.InvalidTokenException;
import com.nhs.individual.Service.AccountService;
import com.nhs.individual.Utils.IUserDetail;
import com.nhs.individual.Utils.RequestUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.nhs.individual.Utils.Constant.AUTH_TOKEN;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private AccountService service;
    @Autowired
    private RequestUtils requestUtils;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull FilterChain filterChain) throws ServletException, IOException,ExpiredJwtException {
        Claims token;
        try{
            token=requestUtils.extractJwtClaimFromCookie(request,AUTH_TOKEN);
        }catch (IllegalArgumentException|InvalidTokenException|ExpiredJwtException e){
            filterChain.doFilter(request,response);
            return;
        }
        if(token==null) {
            filterChain.doFilter(request,response);
            return;
        }else{
            service.findByUsername(token.getSubject())
                    .map(IUserDetail::parseFrom)
                    .ifPresent(user->{
                        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    });
        }
        filterChain.doFilter(request,response);
    }
}
