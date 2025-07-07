//package com.products.produtos.security;
//
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//
//    private final JwtTokenProvider tokenProvider;
//    private final UserDetailsService userDetailsService;
//
//    public JwtTokenFilter(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService) {
//        super();
//        this.tokenProvider = tokenProvider;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String token = tokenProvider.resolveToken( request );
//
//        if(token != null && tokenProvider.validateToken( token ) ) {
//            String username = tokenProvider.extractLoginUser(token);
//
//            Claims claims = tokenProvider.extractClaims(token);
//            String role = claims.get("role", String.class);
//
//            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(username, null, authorities);
//
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter( request, response );
//    }
//
//}
