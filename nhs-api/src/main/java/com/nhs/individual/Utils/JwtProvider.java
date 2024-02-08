package com.nhs.individual.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.catalina.manager.Constants.CHARSET;
@Component
public class JwtProvider {
    private static final String PRIVATE_KEY = "SonNguyenHoangSon";
    private static final Long EXPIRED_TIME=7200L;

    public String generateToken(UserDetails userDetails){
        Date date=new Date(new Date().getTime()+EXPIRED_TIME);
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("alg", "HS256");
        return Jwts.builder()
                .setHeader(headers)
                .setExpiration(date)
                .setIssuedAt(new Date())
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256,PRIVATE_KEY)
                .compact();

    }
    public Claims extractClaims(String token ){
        return Jwts.parser().setSigningKey(PRIVATE_KEY).parseClaimsJws(token).getBody();
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
