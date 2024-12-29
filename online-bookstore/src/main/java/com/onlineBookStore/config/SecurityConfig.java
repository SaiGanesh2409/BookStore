package com.onlineBookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JwtRequestFilter jwtRequestFilter;

	public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(authorize -> authorize
				// Public endpoints
				.requestMatchers( "/users/login","/users/login", "/users/registerCustomer", "/users/registerAdmin").permitAll()
				// Admin-specific endpoints
				.requestMatchers("/users/registerAdmin").hasRole("ADMIN").requestMatchers("/admin/**").hasRole("ADMIN") // Example:
																														// Admin-only
																														// endpoints
				// Customer-specific endpoints
				.requestMatchers("/customer/**").hasRole("CUSTOMER")
				// All other authenticated endpoints
				.requestMatchers("/users/**").authenticated().anyRequest().authenticated() // Secure all other endpoints
		).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
