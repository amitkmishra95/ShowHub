//package com.project.BTS.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    private final OAuthSuccessHandler successHandler;
//
//    public SecurityConfig(OAuthSuccessHandler successHandler) {
//        this.successHandler = successHandler;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/api/**",                 // all your APIs
//                    "/oauth2/**",             // oauth endpoints
//                    "/login/**"
//                ).permitAll()
//                .anyRequest().permitAll()     // allow everything
//            )
//            .oauth2Login(oauth -> oauth
//                .successHandler(successHandler)
//            );
//
//        return http.build();
//    }
//}
//
////@Configuration
////public class SecurityConfig {
////
//////    private final OAuthSuccessHandler successHandler;
//////
//////    public SecurityConfig(OAuthSuccessHandler successHandler) {
//////        this.successHandler = successHandler;
//////    }
//////
//////    @Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////
//////        http.csrf(csrf->csrf.disable())
//////            .authorizeHttpRequests(auth -> auth
//////                .requestMatchers("/api/user/auth/**").permitAll()
//////                .anyRequest().authenticated()
//////            )
//////            .oauth2Login(oauth -> oauth
//////                .successHandler(successHandler)
//////            );
//////
//////        return http.build();
//////    }
////	
////	
////	    @Bean
////	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	        http.csrf(csrf->csrf.disable())
////	            .authorizeHttpRequests(auth -> auth
////	                .anyRequest().permitAll()
////	            )
////	            .formLogin().disable();
////
////	        return http.build();
////	    }
////	
////}
//
