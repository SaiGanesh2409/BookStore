package com.onlineBookStore.jwtutil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    // Token blacklist for invalidated tokens
    private final Set<String> tokenBlacklist = new HashSet<>();

    public JwtUtil(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    // Generate a new token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token) {
        if (tokenBlacklist.contains(token)) {
            return false; // Token has been invalidated
        }
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    // Extract username from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Invalidate a token
    public void invalidateToken(String token) {
        tokenBlacklist.add(token);
    }

}
