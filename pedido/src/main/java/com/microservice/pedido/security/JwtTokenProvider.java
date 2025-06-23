package com.microservice.pedido.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtTokenProvider {


    private static final String SECRET = "eHk10qTbSU9zJY7lCi+sIOL5BJVwN/JRSQByqvgb2ibOVwSINnCBiKXMYx/Zj2xhsbm+QULnUUtvATDjzC1Oag==";
    String secret = Base64.getEncoder().encodeToString(SECRET.getBytes());




    public Authentication getAuth(String token ) {
        UserDetails userDetails = new UserDetails() {
            private static final long serialVersionUID = 1L;
            public Collection< ? extends GrantedAuthority > getAuthorities() {
                return null;
            }
            public String getPassword() {
                return "";
            }
            public String getUsername() {
                return "";
            }
            public boolean isAccountNonExpired() {
                return true;
            }
            public boolean isAccountNonLocked() {
                return true;
            }
            public boolean isCredentialsNonExpired() {
                return true;
            }
            public boolean isEnabled() {
                return true;
            }
        };
        return new UsernamePasswordAuthenticationToken( userDetails, "", userDetails.getAuthorities() );
    }

    public String resolveToken( HttpServletRequest request ) {
        String bearerToken = request.getHeader( "Authorization" );
        if( bearerToken != null && bearerToken.startsWith( "Bearer " ) ){
            return bearerToken.substring( 7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJwts = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                    .build()
                    .parseClaimsJws(token);
            return !claimsJwts.getBody().getExpiration().before(new Date());
        }catch( JwtException | IllegalArgumentException exception ) {
            return false;
        }
    }

}

