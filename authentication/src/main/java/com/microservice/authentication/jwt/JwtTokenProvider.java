package com.microservice.authentication.jwt;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    @Value( "${security.jwt.token.security-key}" )
    private String secretKey;

    @Value( "${security.jwt.token.expire-length}" )
    private long expireLength;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString( secretKey.getBytes() );
    }

    public String createToken( String username, List<String> roles ) {
        Claims claims = Jwts.claims().setSubject( username );
        claims.put( "roles", roles );

        Date now = new Date();
        Date validDate = new Date(now.getTime() + expireLength);

        return Jwts.builder().setClaims( claims ).setIssuedAt( now ).setExpiration( validDate )
                .signWith( SignatureAlgorithm.HS512, secretKey ).compact();
    }

    public Authentication getAuth(String token ) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername( getUserName( token ) );
        return new UsernamePasswordAuthenticationToken
                ( userDetails, "", userDetails.getAuthorities() );
    }

    private String getUserName(String token) {
        return Jwts.parser().setSigningKey( secretKey ).parseClaimsJws( token ).getBody().getSubject();
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
