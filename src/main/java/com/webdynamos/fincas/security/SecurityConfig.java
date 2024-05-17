package com.webdynamos.fincas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;
import com.webdynamos.fincas.filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements ISecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Override
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(ignoreSpecificRequests()).permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private RequestMatcher ignoreSpecificRequests() {
        return new OrRequestMatcher(
            new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.DELETE.name()),
            // Agregar el endpoint /propiedades para todos los métodos HTTP permitidos sin autenticación
            new AntPathRequestMatcher("/propiedades", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/propiedades", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/propiedades", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/propiedades", HttpMethod.DELETE.name()),
            // Agregar el endpoint /arrendadores para todos los métodos HTTP permitidos sin autenticación
            new AntPathRequestMatcher("/arrendadores", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/arrendadores", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/arrendadores", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/arrendadores", HttpMethod.DELETE.name()),
            // Agregar el endpoint /arrendatarios para todos los métodos HTTP permitidos sin autenticación
            new AntPathRequestMatcher("/arrendatarios", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/arrendatarios", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/arrendatarios", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/arrendatarios", HttpMethod.DELETE.name())
        );
    }
    
}
