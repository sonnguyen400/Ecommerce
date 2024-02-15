package com.nhs.individual.Utils;

import com.nhs.individual.Exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.nhs.individual.Utils.Constant.AUTH_TOKEN_AGE;
import static com.nhs.individual.Utils.Constant.REFRESH_TOKEN_AGE;

@Component
public class NewJwtProvider {
    private static final String PRIVATE_KEY = "SonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSon";

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
    }

    public String generateToken(String subject){
        Date date=new Date(new Date().getTime()+AUTH_TOKEN_AGE);
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("alg", "HS256");
        return Jwts.builder()
                .setExpiration(date)
                .setIssuedAt(new Date())
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
    public Claims validate(String token) throws InvalidTokenException,ExpiredJwtException {
        Claims claims=null;
        try{
            claims=extractClaims(token);
        }catch (UnsupportedJwtException|MalformedJwtException|IllegalArgumentException exception){
            exception.printStackTrace();
            throw new InvalidTokenException("Token invalid");
        }

        if(claims==null||claims.getSubject()==null||claims.getSubject().equals("")){
            throw new InvalidTokenException("Token is invalid");
        }
        return claims;
    }
}
