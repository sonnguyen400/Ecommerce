package com.nhs.individual.security.Filter;

import com.nhs.individual.secure.IUserDetail;
import com.nhs.individual.service.AccountService;
import com.nhs.individual.utils.RequestUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.nhs.individual.utils.Constant.AUTH_TOKEN;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private AccountService service;
    @Autowired
    private RequestUtils requestUtils;
    private static final Logger log=LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull FilterChain filterChain) throws ServletException, IOException {
        Claims token= requestUtils.extractJwtClaimFromCookie(request,AUTH_TOKEN);
        if(token!=null&&token.getSubject()!=null&&!token.getSubject().equals("")){
            try {
                service.findByUsername(token.getSubject())
                        .map(IUserDetail::new)
                        .ifPresent(user->{
                            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        });
            } catch (Exception e) {
                log.atError().log("Could not set Authentication");
            }
        }
        filterChain.doFilter(request,response);
    }
}
