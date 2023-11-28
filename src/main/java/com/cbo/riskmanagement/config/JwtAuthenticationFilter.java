//package com.cbo.riskmanagement.config;
//
//import com.cbo.riskmanagement.repository.TokenRepository;
//import com.cbo.riskmanagement.service.JwtService;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//    private final TokenRepository tokenRepository;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws IOException {
//        try {
//
//        if (request.getServletPath().contains("/api/v1/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userName;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7);
//        System.out.println("Toke JWT: "+jwt);
//        userName = jwtService.extractUsername(jwt);
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
//            var isTokenValid = tokenRepository.findByToken(jwt)
//                    .map(t -> !t.isExpired() && !t.isRevoked())
//                    .orElse(false);
//            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }catch (ServletException exception){
//System.out.println("ServletException: "+exception.getMessage()+ " Root Cause: "+exception.getRootCause());
//        }catch (IOException ioException){
//            System.out.println("IOException: "+ioException.getMessage()+ " Root Cause: "+ioException.getStackTrace());
//
//        }
//        }
//}