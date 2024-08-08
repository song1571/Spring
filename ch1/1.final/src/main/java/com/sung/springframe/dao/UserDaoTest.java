// UserDaoTest 클래스 - UserDao를 테스트하는 클래스
package com.sung.springframe.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.springframe.domain.User;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Spring 컨텍스트를 생성하여 DaoFactory 설정을 로드
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class); // userDao 빈을 가져옴
        
        // 새로운 사용자 객체 생성
        User user = new User();
        user.setId("na");
        user.setName("나나");
        user.setPassword("married");

        dao.add(user); // 사용자를 데이터베이스에 추가
            
        System.out.println(user.getId() + " 등록 성공"); // 사용자 등록 성공 메시지 출력
        
        User user2 = dao.get(user.getId()); // 사용자 정보를 데이터베이스에서 가져옴
        System.out.println(user2.getName()); // 사용자 이름 출력
        System.out.println(user2.getPassword()); // 사용자 비밀번호 출력
            
        System.out.println(user2.getId() + " 조회 성공"); // 사용자 조회 성공 메시지 출력
    }
}
