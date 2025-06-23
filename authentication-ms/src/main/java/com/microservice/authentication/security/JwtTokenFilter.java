package com.microservice.authentication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {


    private final JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        super();
        this.tokenProvider = jwtTokenProvider;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = tokenProvider.resolveToken( request);

        if (token != null && tokenProvider.validateToken(token)) {
            Authentication auth = tokenProvider.getAuth(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
