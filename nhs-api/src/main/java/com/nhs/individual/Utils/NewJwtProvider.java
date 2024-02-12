package com.nhs.individual.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class NewJwtProvider {
    private static final String PRIVATE_KEY = "SonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSonSonNguyenHoangSon";
    private static final Long EXPIRED_TIME=7200L;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails){

        Date date=new Date(new Date().getTime()+EXPIRED_TIME);
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("alg", "HS256");

        return Jwts.builder()
                .setExpiration(date)
                .setIssuedAt(new Date())
                .setSubject(userDetails.getUsername())
                .signWith(getKey())
                .compact();
    }
    public Claims extractClaims(String token ){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
    public String getSubject(String token){
        return extractClaims(token).getSubject();
    }
    public String validate(String token){
        Claims claims=null;
        try {
            claims = extractClaims(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(claims==null||
        new Date().after(claims.getExpiration())||
        claims.isEmpty()||
        claims.getSubject()==null||
        claims.getSubject().equals("")) return null;
        return token;
    }
}
