package ma.maane.examjee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Désactiver CSRF pour les tests d'API (à utiliser avec précaution en production)
            .csrf(csrf -> csrf.disable())
            
            // Autoriser tous les endpoints pour les tests
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // Permettre l'accès à la console H2
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        
        return http.build();
    }
} 