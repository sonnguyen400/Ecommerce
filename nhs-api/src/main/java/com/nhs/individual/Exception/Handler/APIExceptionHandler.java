package com.nhs.individual.Exception.Handler;

import com.nhs.individual.Exception.InvalidTokenException;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import com.nhs.individual.Utils.NewJwtProvider;
import com.nhs.individual.Utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class APIExceptionHandler {
    @Autowired
    RequestUtils requestUtils;
    @Autowired
    NewJwtProvider jwtProvider;
    @ExceptionHandler({Exception.class,SQLException.class})
    public ResponseEntity<String> handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity
             .status(HttpStatus.NOT_ACCEPTABLE)
             .body(e.getMessage());
    }
    @ExceptionHandler({ResourceNotFoundException.class,IllegalArgumentException.class})
    public ResponseEntity<ResponseMessage> handleSqlException(SQLException e){
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ResponseMessage> handleAuthenticationException(BadCredentialsException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }
    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ResponseMessage> handleInvalidTokenException(InvalidTokenException e){
        return ResponseEntity
               .status(HttpStatus.NOT_ACCEPTABLE)
               .body(ResponseMessage
                       .builder()
                       .message(e.getMessage())
                       .error()
                       .ok());
    }
//    @ExceptionHandler({ExpiredJwtException.class})
//    public ResponseEntity<ResponseMessage> handleRefreshTokenException(ExpiredJwtException e, HttpServletRequest req, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Claims claims=null;
//        try{
//            claims=requestUtils.extractJwtClaimFromCookie(req,REFRESH_AUTH_TOKEN);
//            HttpHeaders headers=new HttpHeaders();
//            headers.add(HttpHeaders.SET_COOKIE, CookieParse.parseFrom(AuthService.getAuthCookie(AUTH_TOKEN,jwtProvider.generateToken(claims.getSubject()), Integer.parseInt(REFRESH_AUTH_TOKEN))).toString());
//            headers.add(HttpHeaders.SET_COOKIE,CookieParse.parseFrom(AuthService.getAuthCookie(REFRESH_AUTH_TOKEN,jwtProvider.generateRefreshToken(new HashMap<>(), claims.getSubject()), Integer.parseInt(REFRESH_AUTH_TOKEN))).toString());
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(ResponseMessage.builder().message("Resend again").statusCode(REFRESH_STATUS).ok());
//        }catch (IllegalArgumentException| InvalidTokenException| ExpiredJwtException invalidToken){
//            return null;
//        }
//    }


}
