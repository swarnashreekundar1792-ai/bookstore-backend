package com.bookstore.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1: Read the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // Step 2: Check if the header exists and starts with "Bearer "
        // JWT tokens are sent as:  Authorization: Bearer eyJhbGci...
        //if (authHeader != null && authHeader.startsWith("Bearer ")) {
          //  token = authHeader.substring(7); // Remove "Bearer " prefix to get just the token
            //username = jwtUtil.extractUsername(token); // Read username from inside the token
        //}
        System.out.println("AUTH HEADER: " + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            System.out.println("TOKEN: " + token);
            username = jwtUtil.extractUsername(token);
            System.out.println("USERNAME: " + username);
        } else {
            System.out.println("NO BEARER TOKEN FOUND");
        }

        // Step 3: If we got a username and no authentication exists yet for this request
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load the full user details from the database using the username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Step 4: Validate the token against the loaded user details
            System.out.println("TOKEN VALID: " + jwtUtil.validateToken(token));
           // if (jwtUtil.validateToken(token)) {
            if (jwtUtil.validateToken(token)) {

                // Step 5: Create an authentication object and register it with Spring Security
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities() // This carries the user's roles
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // This line tells Spring Security: this request is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 6: Continue to the next filter or controller
        filterChain.doFilter(request, response);
    }
}