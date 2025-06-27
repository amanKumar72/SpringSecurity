package com.aman.SpringSecurityEx.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;


@Service
public class JwtService {

private String secret="amankumarsecretkey72108888390112233445566778899";



    public String generateToken(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] key= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validate(String token, UserDetails userDetails) {

        try {

            String[] parts=token.split("\\.");

            //three parts of jwt token
            if(parts.length!=3){
                return false;
            }

            Claims claims = extractClaims(token);
            String username=claims.getSubject();
            Date exp=claims.getExpiration();
//            if (exp.compareTo(new Date())<0) {
//                return  false;
//            }
            return true;
        }catch (Exception e){
            System.out.println("exception");
            return false;
        }
    }

    private Claims extractClaims(CharSequence token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }
}
