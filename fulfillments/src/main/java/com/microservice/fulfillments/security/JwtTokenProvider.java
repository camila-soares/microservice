//package com.microservice.fulfillments.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.Base64;
//import java.util.Date;
//
//@Service
//public class JwtTokenProvider {
//
//
//
//    private static final String SECRET = "eHk10qTbSU9zJY7lCi+sIOL5BJVwN/JRSQByqvgb2ibOVwSINnCBiKXMYx/Zj2xhsbm+QULnUUtvATDjzC1Oag==";
//    String secret = Base64.getEncoder().encodeToString(SECRET.getBytes());
//
//
//
//
//
//    public String resolveToken( HttpServletRequest request ) {
//        String bearerToken = request.getHeader( "Authorization" );
//        if( bearerToken != null && bearerToken.startsWith( "Bearer " ) ){
//            return bearerToken.substring( 7);
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jws<Claims> claimsJwts = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
//                    .build()
//                    .parseClaimsJws(token);
//            return !claimsJwts.getBody().getExpiration().before(new Date());
//        }catch( JwtException | IllegalArgumentException exception ) {
//            return false;
//        }
//    }
//    public String extractLoginUser(String token) {
//        Claims claims = extractClaims(token);
//        return claims.getSubject();
//    }
//
//    public Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//}
//
