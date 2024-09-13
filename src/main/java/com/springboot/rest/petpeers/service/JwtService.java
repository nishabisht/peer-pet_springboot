//package com.springboot.rest.petpeers.service;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class JwtService {
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//
//    public String generateToken(Authentication authentication){
//      return Jwts.builder()
//              .setSubject(authentication.getName())
//              .setIssuedAt(new Date())
//              .setExpiration(new Date(System.currentTimeMillis()+expiration))
//              .signWith(SignatureAlgorithm.HS512,secret)
//              .compact();
//    }
//}
