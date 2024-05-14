package com.webdynamos.fincas.services;

import java.security.Key;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.webdynamos.fincas.dto.UsuarioDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JWTTokenService {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    @Value("${jwt.secret}")
    private String base64SecretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private Key jwtKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(base64SecretKey);
        jwtKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
    }

    /**
     * Generates a JWT token for the given user details.
     * @param usuario the user information to include in the JWT.
     * @return a JWT string
     */
    public String generarToken(UsuarioDTO usuario) {
        try {
            Claims claims = Jwts.claims().setSubject(usuario.getCorreo());
            claims.put("roles", usuario.getRoles()); // Ensure UsuarioDTO has roles field

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationTime);

            return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtKey, SignatureAlgorithm.HS512)
                .compact();
        } catch (Exception e) {
            logger.error("Error generating JWT token for user {}: ", usuario.getCorreo(), e);
            throw new IllegalStateException("Failed to generate JWT token", e);
        }
    }

    public String getUsername(String jwtToken){
        return decodeToken(jwtToken).getSubject();
    }

    public Date getFechaExpiracion(String jwtToken){
        return decodeToken(jwtToken).getExpiration();
    }

    public Claims decodeToken(String jwtToken) {
        return Jwts.parserBuilder()
                    .setSigningKey(jwtKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
    }
}
