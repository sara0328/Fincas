package com.webdynamos.fincas.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webdynamos.fincas.dto.UsuarioDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JWTTokenService {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    @Value("${jwt.secret}")
    private String secret; // Elimina el valor predeterminado para asegurar que se use la configuración externa

    @Value("${jwt.expiration}")
    private long jwtExpiration; // Elimina el valor predeterminado

    private Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Considera externalizar esta llave también

    public String generarToken(UsuarioDTO usuario) {
        ObjectMapper objectMapper = new ObjectMapper();
        String username = "";
        try {
            username = objectMapper.writeValueAsString(usuario);
        } catch (Exception e) {
            logger.error("Error serializando el usuario: {}", e.getMessage());
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .signWith(jwtKey, SignatureAlgorithm.HS512) // Use your appropriate signing algorithm
                .compact();
    }

    public String getUsername(String jwtToken){
        return decodificarToken(jwtToken).getSubject();
    }

    public Date getFechaExpiracion(String jwtToken){
        return decodificarToken(jwtToken).getExpiration();
    }

    public Claims decodificarToken(String jwtToken) {
        return Jwts.parserBuilder()
                            .setSigningKey(jwtKey)
                            .build()
                            .parseClaimsJws(jwtToken)
                            .getBody();
    }
}
