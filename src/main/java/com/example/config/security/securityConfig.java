package com.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 최신 문법으로 모든 보안 설정 비활성화
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll()
                        .anyRequest().permitAll()
                )
                // 폼 로그인 비활성화 (람다 사용)
                .formLogin(form -> form.disable())
                // HTTP 기본 인증 비활성화 (람다 사용)
                .httpBasic(basic -> basic.disable())
                // CSRF 보호 비활성화 (람다 사용)
                .csrf(csrf -> csrf.disable())
                // CORS 비활성화 (람다 사용)
                .cors(cors -> cors.disable())
                // 헤더 설정 (람다 사용)
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable())
                );

        return http.build();
    }
}