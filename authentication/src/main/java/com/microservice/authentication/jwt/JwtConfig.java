package com.microservice.authentication.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtConfig extends SecurityConfigurerAdapter< DefaultSecurityFilterChain, HttpSecurity > {


    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtConfig( JwtTokenProvider jwtTokenProvider ) {
        super();
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore( jwtTokenFilter, UsernamePasswordAuthenticationFilter.class );
    }
}
