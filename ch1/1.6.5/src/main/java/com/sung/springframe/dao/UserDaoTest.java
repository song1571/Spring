package com.sung.springframe.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.springframe.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("song");
		user.setName("백기선");
		user.setPassword("married");

		try {
			dao.add(user);
			System.out.println(user.getId() + " 등록 성공");
		} catch (SQLException e) {
			System.out.println(user.getId() + " 등록 실패: " + e.getMessage());
		}

		try {
			User user2 = dao.get(user.getId());
			System.out.println(user2.getName());
			System.out.println(user2.getPassword());
			System.out.println(user2.getId() + " 조회 성공");
		} catch (SQLException e) {
			System.out.println("사용자 조회 실패: " + e.getMessage());
		}
	}
}
// 수정 이유: 동일한 ID를 가진 사용자를 추가하려고 할 때 발생하는 예외를 처리하기 위해 try-catch 블록을 추가했습니다.
// 이는 유치원생도 쉽게 이해할 수 있도록 설명하자면, "이미 등록된 친구를 다시 등록하려고 하면 등록 실패라고 알려주는 것"과 같습니다.