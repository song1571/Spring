package com.sung.springframe.learningtest.daotest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.sung.springframe.dao.UserDao;
import com.sung.springframe.dao.UserDaoJdbc;
import com.sung.springframe.dao.UserDaoSql;

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
        UserDaoJdbc userDao = new UserDaoJdbc();  // UserDao 인터페이스의 구현체인 UserDaoJdbc 사용
        userDao.setDataSource(dataSource());  // setDataSource 메서드 호출
        return userDao;
    }
    
    @Bean
    public UserDaoSql userdaosql() {
		return new UserDaoSql();
    	
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
