package com.microservice.authentication.security;

import com.microservice.authentication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    public static final int EXPIRATION = 360000000;
    LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(EXPIRATION);
    Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
    Date data = Date.from(instant);

    String hourExpirationToken = dateHourExpiration.withHour(22).format(DateTimeFormatter.ofPattern("HH:mm"));

    private static final String SECRET = "eHk10qTbSU9zJY7lCi+sIOL5BJVwN/JRSQByqvgb2ibOVwSINnCBiKXMYx/Zj2xhsbm+QULnUUtvATDjzC1Oag==";

    String secret = Base64.getEncoder().encodeToString(SECRET.getBytes());



    public String createToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getUserRole())
                .setExpiration(data)
                .claim("ExpirationHours", hourExpirationToken)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> getRoles(String token) {
        Object roles = getClims(token).get("roles");
        if (roles instanceof List<?>){
            return ((List<String>) roles).stream().map(Objects::toString).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

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
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
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


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJwts = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                    .build()
                    .parseClaimsJws(token);
            return !claimsJwts.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
