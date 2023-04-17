package com.booking.bookmyroom.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public Set<String> idempotentKeys() {
		return new HashSet<String>();
	}
}
