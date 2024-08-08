package com.sung.springframe.daotest;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.springframe.dao.DaoFactory;
import com.sung.springframe.dao.UserDao;
import com.sung.springframe.domain.User;

public class UserDaoTest {
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
		Assertions.assertEquals(dao.getCount(), 0);
		
		User user = new User();
		user.setId("gyumee");
		user.setName("hello");
		user.setPassword("springno1");

		dao.add(user);
		Assertions.assertEquals(dao.getCount(), 1);
		
		User user2 = dao.get(user.getId());
		
		Assertions.assertEquals(user2.getName(), user.getName());
		Assertions.assertEquals(user2.getPassword(), user.getPassword()); // Here!!!
	}
}