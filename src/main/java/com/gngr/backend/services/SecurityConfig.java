package com.gngr.backend.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(requests -> requests
    // .requestMatchers("/auth/send-otp",
    // "/auth/verify-otp",
    // "/swagger-ui/**",
    // "/v3/api-docs/**",
    // "/swagger-resources/**",
    // "/webjars/**")
    // .permitAll()
    // .anyRequest().authenticated())
    // .sessionManagement(management ->
    // management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    // http.addFilterBefore(jwtAuthFilter,
    // UsernamePasswordAuthenticationFilter.class);

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll()); 

        return http.build();
    }

}
