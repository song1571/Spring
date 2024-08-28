package com.sung.springframe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigA {

	@Bean
	public Aaa aaa() {
		return new Aaa();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
