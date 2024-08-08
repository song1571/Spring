package com.sung.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMMaker()); // Dependent Injection
													  // :Constructor argument
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMMaker() {
		ConnectionMaker connectionMaker = 
				new MConnectionMaker();
		return connectionMaker;
	}
	
	@Bean
	public ConnectionMaker connectionHMaker() {
		ConnectionMaker connectionMaker = 
				new HConnectionMaker();
		return connectionMaker;
	}
}
