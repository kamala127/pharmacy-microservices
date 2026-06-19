package com.pharma.medicine_service.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.ServletException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("JWT Filter Called");

        String authHeader =
                request.getHeader("Authorization");

        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request,response);
            return;
        }

        String token =
                authHeader.substring(7);

        System.out.println("Token = " + token);
        System.out.println(
                "Token Valid = "
                        + jwtService.isTokenValid(token));
        if(jwtService.isTokenValid(token)
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null) {

            String username =
                    jwtService.extractUsername(token);

            List<String> roles =
                    jwtService.extractRoles(token);

            System.out.println("Username = " + username);
            System.out.println("Roles = " + roles);

            List<GrantedAuthority> authorities =
                    roles.stream()
                            .map(role ->
                                    (GrantedAuthority)
                                            new SimpleGrantedAuthority(role))
                            .toList();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
