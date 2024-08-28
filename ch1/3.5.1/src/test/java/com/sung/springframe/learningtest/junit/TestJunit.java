package com.sung.springframe.learningtest.junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intheeast.springframe.learningtest.MyCustomObject;
import com.intheeast.springframe.learningtest.MyCustomObjectFactoryBean;

@Configuration
public class TestJunit {
	
	@Bean
	public MyCustomObjectFactoryBean myCustomObjectFactory() {
		MyCustomObjectFactoryBean factoryBean = new MyCustomObjectFactoryBean();
		return factoryBean;
	}
	
	@Bean
	public MyCustomObject myCustomObject() throws Exception {
		return myCustomObjectFactory().getObject();
	}
}
