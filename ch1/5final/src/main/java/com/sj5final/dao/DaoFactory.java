package com.sj5final.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sj5final.service.UserService;
@Configuration
public class DaoFactory {
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}
	@Bean
	public JavaMailSenderImpl mailSenderImpl() {
		// https://goodteacher.tistory.com/8 참조
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		
		mailSenderImpl.setJavaMailProperties(properites());
		
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587); // TLS : 587, SSL : 465
		// 구글 계정이 swseokitec@gmail.com인 경우,
		// SMTP 설정에서 사용할 사용자 이름(User name)은
		// 이 이메일 주소 그대로인 swseokitec@gmail.com입니다.
		// SMTP 설정 시 이메일 주소 전체를 사용자 이름으로 사용하는 것이 일반적입니다.
		mailSenderImpl.setUsername("tjrwn1234562@gmail.com");
		mailSenderImpl.setPassword("mmfzdmpqjwlxhwcj");
		return mailSenderImpl;
	}
	
	@Bean
	public Properties properites() {
		Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
	}
	
	
	// 우리가 직접 configuration 한다.
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
		return dataSourceTransactionManager;
	}
	
	@Bean
	public UserDaoJdbc UserDaoSql() {
		UserDaoJdbc userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;
	}	
	@Bean
	public UserService userserver(UserDao userDao)
	{
		UserService us = new UserService();
		us.setMailSender(mailSenderImpl());
		us.setTransactionManager(transactionManager());
		us.setUserDao(userDao);
		return us;
	}

}