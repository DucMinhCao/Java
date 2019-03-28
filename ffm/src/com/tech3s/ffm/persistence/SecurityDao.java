package com.tech3s.ffm.persistence;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tech3s.ffm.model.User;

public class SecurityDao {

	public User authenticate(String email, String password) {
		User user = new User();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// STEP 3: Open a connection			
			conn = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// STEP 4: Execute a query
			String querySQL = "SELECT id, age, name, position "
							+ "FROM users "
							+ "WHERE email = ? "
							+ "  AND password = ?";
			pstmt = conn.prepareStatement(querySQL);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			System.out.println("Executing query: " + querySQL + " - " + email + " | " + password);
			ResultSet rs = pstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				user.setId(rs.getInt("id"));
				user.setAge(rs.getInt("age"));
				user.setEmail(email);
				user.setName(rs.getString("name"));
				user.setPassword(password);
				user.setPosition(rs.getString("position"));			
			}
			
			// STEP 6: Clean-up environment
			rs.close();
			pstmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} 
		catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} 
			catch (SQLException se2) {
			} // nothing we can do
			
			try {
				if (conn != null) {
					conn.close();
				}
			} 
			catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		
		return user;
	}
}
