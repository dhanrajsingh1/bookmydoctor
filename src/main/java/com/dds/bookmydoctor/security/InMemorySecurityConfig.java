//package com.dds.bookmydoctor.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class InMemorySecurityConfig {
//
//  @Bean
//  public InMemoryUserDetailsManager userDetailsManager() {
//    UserDetails dds = User.builder().username("DDS").password("{noop}Dds@123").roles("ADMIN")
//        .build();
//    UserDetails nikhil = User.builder().username("Nikhil").password("{noop}Nikhil@123")
//        .roles("MANAGER")
//        .build();
//    UserDetails sumeet = User.builder().username("Sumeet").password("{noop}Sumeet@123")
//        .roles("USER")
//        .build();
//
//    return new InMemoryUserDetailsManager(dds, nikhil, sumeet);
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(configurer ->
//        configurer
//            .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("USER"));
//
//    http.httpBasic(Customizer.withDefaults());
//    http.csrf(csrf -> csrf.disable());
//    return http.build();
//  }
//}
