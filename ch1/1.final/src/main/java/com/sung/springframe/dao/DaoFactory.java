// DaoFactory 클래스 - Spring 설정 클래스, 데이터베이스와 DAO를 설정
package com.sung.springframe.dao;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

    // MySQL 데이터베이스 연결 설정
    @Bean
    public DataSource dataSourceM() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        
        return dataSource;
    }

    // H2 데이터베이스 연결 설정
    @Bean
    public DataSource dataSourceH() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("1234");
        
        return dataSource;
    }

    // UserDao 빈 설정
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSourceM()); // MySQL 데이터베이스를 사용
        return userDao;
    }
}
