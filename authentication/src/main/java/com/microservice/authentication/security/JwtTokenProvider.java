package com.microservice.authentication.security;

import com.microservice.authentication.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.security.oauth2.resourceserver.jwt.secret}")
    private String base64Key;

    @Value("${spring.security.oauth2.resourceserver.jwt.validity}")
    private long validityInMs;

    private final UserDetailsService userDetailsService;


    private JwtParser parser;
    @PostConstruct
    public void init() {
         parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key)))
                .build();
    }

    public String createToken(User user) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("roles", user.getUserRole().name())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, base64Key)
                .compact();
    }


    public Claims getClims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    public List<String> getRoles(String token) {
//        Object roles = getClims(token).get("roles");
//        if (roles instanceof List<?>)
//            return ((List<String>) roles).stream().map(Objects::toString).collect(Collectors.toList());
//        return Collections.emptyList();
//    }

    public Authentication getAuth(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        Claims claims = getClims(token);
        String role = claims.get("roles", String.class);

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));


        return new UsernamePasswordAuthenticationToken
                (userDetails, "",authorities);
    }



    public String getUserName(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key)))
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

//    public Jws<Claims> parseToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token);
//    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = parser.parseClaimsJws(token);

            return jws.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
