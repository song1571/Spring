package com.sung.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sung.springframe.domain.User;

public class UserDaoSql {
	
//	public UserDaoSql() {
//		
//	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.Mysql.cj.jdbc.Driver");
		
		Connection c = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8",
						"root",
						"1234"
						);
				
		return c;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
