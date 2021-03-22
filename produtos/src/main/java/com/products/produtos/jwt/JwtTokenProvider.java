package com.products.produtos.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {


    @Value( "${security.jwt.token.security-key}" )
    private String secretKey;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString( secretKey.getBytes() );
    }

    public Authentication getAuth(String token ) {
        UserDetails userDetails = new UserDetails() {
            private static final long serialVersionUID = 1L;

            public boolean isEnabled() {
                return true;
            }
            public boolean isCredentialsNonExpired() {
                return true;
            }
            public boolean isAccountNonLocked() {
                return true;
            }
            public boolean isAccountNonExpired() {
                return true;
            }
            public String getUsername() {
                return "";
            }
            public String getPassword() {
                return "";
            }
            public Collection< ? extends GrantedAuthority > getAuthorities() {
                return null;
            }
        };
        return new UsernamePasswordAuthenticationToken( userDetails, "", userDetails.getAuthorities() );
    }

    public String resolveToken( HttpServletRequest request ) {
        String bearerToken = request.getHeader( "Authorization" );
        if( bearerToken != null && bearerToken.startsWith( "Bearer " ) ){
            return bearerToken.substring( 7, bearerToken.length() );
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJwts = Jwts.parser().setSigningKey( secretKey ).parseClaimsJws( token );
            if(claimsJwts.getBody().getExpiration().before( new Date() )) {
                return false;
            }
            return true;
        }catch( JwtException | IllegalArgumentException exception ) {
            return false;
        }
    }
}
