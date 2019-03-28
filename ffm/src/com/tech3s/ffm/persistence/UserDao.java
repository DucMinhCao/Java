package com.tech3s.ffm.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tech3s.ffm.model.User;

public class UserDao {

	public User create (User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// Open a connection			
			connection = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// Execute a query
			String insertSQL = "INSERT INTO users (age, email, name, password, position) "
							 + "VALUES (?, ?, ?, ?, ?) ";
			pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, user.getAge());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPosition());
			System.out.println("Executing insert query: " + insertSQL + " - " + user);
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }	        
			
			// Clean-up environment
			pstmt.close();
			connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			} 
		} 
		
		return user;
	}
	
	public User read(String id) {
		User user = new User();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// Open a connection			
			connection = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// Execute a query
			String querySQL = "SELECT id, age, email, name, password, position "
							+ "FROM users "
							+ "WHERE email = ? "
							+ "  AND id = ?";
			pstmt = connection.prepareStatement(querySQL);
			pstmt.setString(1, id);			
			System.out.println("Executing query: " + querySQL + " - " + id);
			ResultSet rs = pstmt.executeQuery();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve data by column name
				user.setId(rs.getInt("id"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPosition(rs.getString("position"));			
			}
			
			// Clean-up environment
			rs.close();
			pstmt.close();
			connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			} 
		} 
		
		return user;
	}

	public User update (User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// Open a connection			
			connection = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// Execute a query
			String updateSQL = "UPDATE users "
							 + "SET age = ?, email = ?, name = ?, password = ?, position = ? "
							 + "WHERE id = ?";
			pstmt = connection.prepareStatement(updateSQL);
			pstmt.setInt(1, user.getAge());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPosition());
			pstmt.setInt(6, user.getId());
			System.out.println("Executing update query: " + updateSQL + " - " + user);
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Updating user failed, no rows affected.");
	        }        
			
			// Clean-up environment
			pstmt.close();
			connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			} 
		} 
		
		return user;
	}
	
	public boolean delete (int id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// Open a connection			
			connection = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// Execute a query
			String deleteSQL = "DELETE FROM users "
							 + "WHERE id = ? ";
			pstmt = connection.prepareStatement(deleteSQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);			
			System.out.println("Executing delete query: " + deleteSQL + " - id = " + id);
			pstmt.executeUpdate();						      
			
			// Clean-up environment
			pstmt.close();
			connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			} 
		} 
		
		return true;
	}
	
	public List<User> list() {
		List<User> users = new ArrayList<>();
		
		Connection connection = null;
		Statement stmt = null;
		try {
			// Register JDBC driver
			Class.forName(ConstantDao.JDBC_DRIVER);

			// Open a connection			
			connection = DriverManager.getConnection(ConstantDao.DB_URL, ConstantDao.USER, ConstantDao.PASS);

			// Execute a query
			String querySQL = "SELECT id, age, email, name, password, position "
							+ "FROM users ";							
			stmt = connection.createStatement();
			System.out.println("Executing query: " + querySQL);
			ResultSet rs = stmt.executeQuery(querySQL);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve data by column name
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPosition(rs.getString("position"));
				users.add(user);
			}
			
			// Clean-up environment
			rs.close();
			stmt.close();
			connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (stmt != null) {
					stmt.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			} 
		} 
		
		return users;
	}
}
