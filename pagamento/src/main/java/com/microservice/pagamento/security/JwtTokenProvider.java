package com.microservice.pagamento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
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


//    @Value( "${security.jwt.token.security-key}" )
//    private String secretKey;

    private static final String secretKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYW1pbGEiLCJyb2xlcyI6WyJBZG1pbiJdLCJpYXQiOjE3NTA2MjM0MjgsImV4cCI6MTc1MDYyMzc4OH0.j9ltzvxgkHVSynLwABnUOKLTxca1FEclnvHt0rmXw60";


    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

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
            Jws<Claims> claimsJwts = Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return !claimsJwts.getBody().getExpiration().before(new Date());
        }catch( JwtException | IllegalArgumentException exception ) {
            return false;
        }
    }

}

