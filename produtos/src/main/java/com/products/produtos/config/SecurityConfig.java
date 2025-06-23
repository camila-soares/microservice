package com.products.produtos.config;


import com.products.produtos.security.JwtTokenFilter;
import com.products.produtos.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig  {


    private final JwtTokenProvider provider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()
                    ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            return http.build();
    }
}
