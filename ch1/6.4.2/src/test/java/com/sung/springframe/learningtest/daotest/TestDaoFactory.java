package com.sung.springframe.learningtest.daotest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.sung.springframe.dao.UserDao;
import com.sung.springframe.dao.UserDaoJdbc;

@Configuration
public class TestDaoFactory {

	    @Bean
	    public DataSource dataSource() {
	    	
	        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

	        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
	        dataSource.setUrl("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8");
	        dataSource.setUsername("root");
	        dataSource.setPassword("1234");
	        
	        return dataSource;
	    }

	    @Bean
	    public UserDao userDao() {
	        UserDao userDao = new UserDaoJdbc();
	        userDao.setDataSource(dataSource()); 
	        
	        return userDao;   
	        
	    }
	        
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
