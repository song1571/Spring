package com.sung.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sung.springframe.domain.User;

public class UserDao {
	private ConnectionMaker connectionMaker;

	public void setConnectionMaker(ConnectionMaker simpleConnectionMaker) {
		this.connectionMaker = simpleConnectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
			"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) { // 수정: 결과가 없을 때의 상황 처리 추가
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));

			rs.close();
			ps.close();
			c.close();

			return user;
		} else {
			rs.close();
			ps.close();
			c.close();
			throw new SQLException("User not found for ID: " + id); // 수정: 결과가 없을 때 예외 발생
		}
	}
}
// 수정 이유: 결과가 없을 때의 상황을 처리하기 위해 if 조건문을 추가하고, 결과가 없을 경우 SQLException을 발생시키도록 하였습니다. 
// 이는 유치원생도 쉽게 이해할 수 있도록 설명하자면, 
// "친구를 찾으러 갔는데 친구가 없으면 친구가 없다고 알려주는 것"과 같습니다.