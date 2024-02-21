package com.nhs.individual.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.nhs.individual.Utils.Constant.REFRESH_TOKEN_AGE;

@Component
public class JwtProvider {
    @Value("${nhs.token.accessTokenms}")
    private int ACCESS_TOKEN_EXPIRED;
    private static final String PRIVATE_KEY = "SonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSon";
    private final Logger log= LoggerFactory.getLogger(JwtProvider.class);
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
    }
    public String generateToken(String subject){
        Date date=new Date(new Date().getTime()+ACCESS_TOKEN_EXPIRED);
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("alg", "HS256");
        return Jwts.builder()
                .setExpiration(date)
                .setIssuedAt(date)
                .setSubject(subject)
                .signWith(getKey())
                .compact();
    }
    public String generateRefreshToken(Map<String,?> extraClaims,String subject){
        Date date=new Date(new Date().getTime()+REFRESH_TOKEN_AGE);
        Map<String,Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setExpiration(date)
                .setIssuedAt(new Date())
                .setSubject(subject)
                .signWith(getKey())
                .compact();
    }
    public Claims extractClaims(String token ) throws ExpiredJwtException, UnsupportedJwtException,MalformedJwtException,IllegalArgumentException {
       return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

    }
    public String getSubject(String token){
        return extractClaims(token).getSubject();
    }
    public Claims validate(String token) {
        Claims claims=null;
        try{
            claims=extractClaims(token);
            return claims;
        }catch (UnsupportedJwtException|MalformedJwtException|IllegalArgumentException exception){
            log.atWarn().log("Token is invalid");
        }catch (ExpiredJwtException e){
            log.atWarn().log("Token is expired");
        }
        return null;
    }
}
