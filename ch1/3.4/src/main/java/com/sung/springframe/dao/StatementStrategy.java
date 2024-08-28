package com.sung.springframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	PreparedStatement makePreparedStatement(Connection c) throws SQLException; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
