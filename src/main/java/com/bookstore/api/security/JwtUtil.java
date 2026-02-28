package com.bookstore.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // This is the secret key used to sign and verify tokens
    // In a real production app this would be stored in environment variables, not hardcoded
    private static final String SECRET_STRING = "bookstoreSecretKey2024bookstoreSecretKey2024bookstoreSecretKey2024";

    // Token is valid for 24 hours (in milliseconds)
    private static final long EXPIRATION_TIME = 86400000;

    // Convert the secret string into a cryptographic Key object
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    }

    // Called after successful login — generates a new token for the user
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    // Extracts the username from inside the token
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_STRING.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Checks if the token is valid and not expired
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_STRING.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}