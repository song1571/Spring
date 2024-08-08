package com.sung.springframe.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sung.springframe.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		AnnotationConfigApplicationContext context = 
//				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("kim");
		user.setName("민기");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " 조회 성공");
	}
}
