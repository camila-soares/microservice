package com.microservice.authentication.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {


    private final JwtTokenProvider tokenProvider;
    @Autowired
    public JwtTokenFilter( JwtTokenProvider jwtTokenProvider ) {
        super();
        this.tokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter( ServletRequest request,
                          ServletResponse response,
                          FilterChain chain ) throws IOException, ServletException {

        String token = tokenProvider.resolveToken( (HttpServletRequest) request );

        if(token != null && tokenProvider.validateToken( token ) ) {
            Authentication auth = tokenProvider.getAuth( token );
            if(auth  != null ) {
                SecurityContextHolder.getContext().setAuthentication( auth );
            }
        }
        chain.doFilter( request, response );

    }
}
