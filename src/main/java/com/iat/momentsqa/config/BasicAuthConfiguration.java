//package com.iat.momentsqa.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@Order(1)
//public class BasicAuthConfiguration {
//    @Bean
//    public SecurityFilterChain BasicAuth(HttpSecurity http) throws Exception {
//        http.cors(cors -> cors.disable())
//                .securityMatcher(AntPathRequestMatcher.antMatcher("/channels/**"))
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth ->
//                        auth.anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}
