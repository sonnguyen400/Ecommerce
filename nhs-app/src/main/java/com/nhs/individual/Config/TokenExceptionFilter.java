//package com.nhs.individual.Config;
//
//import com.nhs.individual.Exception.InvalidTokenException;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//@Component
//public class TokenExceptionFilter extends OncePerRequestFilter {
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver exceptionResolver;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try{
//            filterChain.doFilter(request,response);
//        }catch (ExpiredJwtException e){
//            exceptionResolver.resolveException(request,response,filterChain,e);
//        }catch(InvalidTokenException e){
//            System.out.println("Invalid token");
//        }
//    }
//}
