package com.example.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CustomJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Example: Adding a grace period of 5 minutes (300 seconds) for clock skew
    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        long skewAllowanceMillis = 5 * 60 * 1000; // 5 minutes in milliseconds
        return expirationDate.getTime() + skewAllowanceMillis < System.currentTimeMillis();
    }


    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            // Set a long expiration time (e.g., 1 year)
            .setExpiration(new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000)) // 1 year
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }
    

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Ignore expiration check (not recommended)
            return e.getClaims();
        } catch (JwtException e) {
            throw new CustomJwtException("Invalid JWT token", e);
        }
    }
    
// private Claims extractAllClaims(String token) {
//     try {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSignInKey()) // Make sure getSignInKey() returns the correct signing key
//                 .build()
//                 .parseClaimsJws(token)  // Parse the token
//                 .getBody();  // Extract claims body
//     } catch (ExpiredJwtException e) {
//         throw new CustomJwtException("JWT token is expired", e); // Handle expired token
//     } catch (JwtException e) {
//         throw new CustomJwtException("Invalid JWT token", e); // Handle invalid token
//     }
// }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
