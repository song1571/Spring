package com.intheeast.springframe.learningtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean  // 자바기반에 컨프레이션이다 
	public MyCustomObjectFactoryBean myCustomObjectFactory() {
		MyCustomObjectFactoryBean factoryBean = new MyCustomObjectFactoryBean();
		return factoryBean;
	}
	
	@Bean
	public MyCustomObject myCustomObject() throws Exception {
		return myCustomObjectFactory().getObject();
	}

}