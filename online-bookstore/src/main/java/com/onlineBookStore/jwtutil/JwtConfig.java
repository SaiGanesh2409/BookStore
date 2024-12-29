package com.onlineBookStore.jwtutil;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

	@Bean
	public SecretKey secretKey() {
		// Generate a strong secret key for HS256
		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
}