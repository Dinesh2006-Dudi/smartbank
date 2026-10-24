package com.smartbank.auth.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtil {
    private String SECRET = "[STRIPPED 74 bytes]";
    private long EXPIRATION = 86400000;
    public String generateToken(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }
}