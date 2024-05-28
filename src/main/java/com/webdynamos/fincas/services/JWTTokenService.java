package com.webdynamos.fincas.services;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JWTTokenService {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    private long jwtExpiration = 99999999;
    private Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // You need to set this key appropriately

    /**
     * Generates a JWT token for the given user details.
     * @param usuario the user information to include in the JWT.
     * @return a JWT string
     */
    public String generarToken(ArrendadorConPasswordDTO usuario) {
        try {
            Claims claims = Jwts.claims().setSubject(usuario.getUsername());
            claims.put("id", usuario.getId_arrendador()); // Ensure ArrendadorConPasswordDTO has id_arrendador field

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtExpiration);
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

            return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtKey, SignatureAlgorithm.HS512)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .compact();
        } catch (Exception e) {
            logger.error("Error generating JWT token for user {}: ", usuario.getCorreo(), e);
            throw new IllegalStateException("Failed to generate JWT token", e);
        }
    }

    public String getUsername(String jwtToken) {
        return decodeToken(jwtToken).getSubject();
    }

    public Date getFechaExpiracion(String jwtToken) {
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
