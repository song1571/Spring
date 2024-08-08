package com.sung.springframe.daotest;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.springframe.dao.DaoFactory;
import com.sung.springframe.dao.UserDao;
import com.sung.springframe.domain.User;

public class UserDaoTestSet {
	@Test 
	public void andAndGet() throws SQLException {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// boolean condition = true;
		// ...
		// condition = false;(ㅅ겯)
		// ...
		// assert condition -> 프로그램 중단되고 AssertError 발생
		//
		// Assert that expected and actual are equal.
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
	

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(), user1.getPassword());
		
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());
	}
	@Test
	public void count() throws SQLException {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		User user3 = new User("bumjin", "박범진", "springno3");
				
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
				
		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);
	}
}
