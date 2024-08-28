package com.sung.springframe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigA.class)
public class ConfigB {

	@Bean
	public Bbb bbb() {
		return new Bbb();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
