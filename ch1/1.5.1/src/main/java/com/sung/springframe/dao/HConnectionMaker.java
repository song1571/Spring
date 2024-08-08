package com.sung.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HConnectionMaker implements ConnectionMaker {
	
	public HConnectionMaker() {}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		//Class.forName("org.h2.Driver");
		// jdbc:h2:tcp://localhost/~/test
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test",
				"sa",
				"1234");
		
		return c;
	}

}
