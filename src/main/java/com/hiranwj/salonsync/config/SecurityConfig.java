package com.hiranwj.salonsync.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String role = jwt.getClaimAsString("role"); // token has "role": "ADMIN"
            if (role == null) return List.of();
            return List.of(new SimpleGrantedAuthority("ROLE_" + role)); // converts to ROLE_ADMIN
        });
        return converter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Simple decoder that skips validation (for local development only)
        return token -> {
            Map<String, Object> headers = new HashMap<>();
            headers.put("alg", "none");

            Jwt jwt = Jwt.withTokenValue(token)
                    .headers(h -> h.putAll(headers))
                    .claim("role", "ADMIN")
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(3600))
                    .build();
            return jwt;
        };
    }

//    In production, need to configure the real JWT decoder:
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri("https://your-auth-server/.well-known/jwks.json").build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        log.info("inside securityFilterChain method:");
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/stylist")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/appointment")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/appointments")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/stylistData", "POST")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/stylistData", "GET")).permitAll()
                        .anyRequest().authenticated()

                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );
        return http.build();
    }
}
