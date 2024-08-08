// UserDao 클래스 - 데이터베이스와 사용자 데이터를 주고 받는 클래스
package com.sung.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.sung.springframe.domain.User;

public class UserDao {
    // 데이터베이스 연결을 위한 DataSource 변수
    private DataSource dataSource;
    
    // DataSource를 설정하는 메소드
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 사용자를 데이터베이스에 추가하는 메소드
    public void add(User user) throws SQLException {
        // 데이터베이스 연결
        Connection c = this.dataSource.getConnection();

        // SQL 문을 준비하고 실행
        PreparedStatement ps = c.prepareStatement(
            "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate(); // SQL 문 실행

        ps.close(); // PreparedStatement 닫기
        c.close(); // Connection 닫기
    }

    // 사용자 ID로 사용자를 가져오는 메소드
    public User get(String id) throws SQLException {
        // 데이터베이스 연결
        Connection c = this.dataSource.getConnection();
        // SQL 문을 준비하고 실행
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery(); // 결과를 가져옴
        rs.next(); // 결과 집합의 첫 번째 행으로 이동
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close(); // ResultSet 닫기
        ps.close(); // PreparedStatement 닫기
        c.close(); // Connection 닫기

        return user; // User 객체 반환
    }
}
