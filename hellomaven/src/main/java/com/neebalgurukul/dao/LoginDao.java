package com.neebalgurukul.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
//import java.time.LocalDate;
import java.util.List;

import com.neebalgurukul.model.Login;

import java.sql.ResultSet;

import com.neebalgurukal.exceptions.UserAlreadyExistsException;
//import package neebal.gurukal.exceptions;
import com.neebalgurukal.exceptions.UserNotFoundException;


public class LoginDao {
	Connection conn =null;
	Statement stmt = null;
	ResultSet rs = null;
	DataSource ds = new DataSource();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public  List<Login> getLoginDetails() throws SQLException {
		String query = "select username, password from login";
		conn=ds.getConnection();
		List<Login> loginList = new ArrayList<Login>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs!=null) {
				while(rs.next()) { //goes i=0 to n for ur query
					Login loginObj = new Login();
					loginObj.setUname(rs.getString("username")); //gets ith record at column username in sql query output
					loginObj.setPswd(rs.getString("password"));
					
					loginList.add(loginObj);
				}
			}
			
		
	}
		catch (SQLException e) {
			throw new SQLException();
		}
		finally {
		
			conn.close();
		}
		
		
		return loginList;

}
	
	
	public  Login getLoginPassword(String username) throws Exception {
		Login loginObj = new Login();
		String query = "select username, password from login where username = ? and active = ?";
		conn=ds.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,username);
			pstmt.setBoolean(2, true);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) { //goes i=0 to n for ur query
					
					loginObj.setUname(rs.getString("username")); //gets ith record at column username in sql query output
					loginObj.setPswd(rs.getString("password"));
					
					
				}
			}

		} catch (SQLException e) {
			throw new UserNotFoundException();
		}
		finally {
			conn.close();
		}
		return loginObj;
			
}
	
	
	public  boolean insertRecord(String username, String password, Date lastModifiedDate,boolean active) throws Exception {
		
		String query = "insert into login (username,password,lastModifiedDate,active) values(? , ? , ? , ?);";
		conn=ds.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setDate(3, lastModifiedDate);
			pstmt.setBoolean(4, true);
			if(pstmt.executeUpdate()<=0) {
				throw new UserAlreadyExistsException();
			}
			}
		
		catch(UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException();
		}
			
		catch (SQLException e) {
			System.out.println("SQLException: "+e.getMessage());
			throw new SQLException(e);
		}
		finally {
			conn.close();
		}
		return true;
			


		
	
	
	
	}
	
	public  boolean deleteRecordSoft(String username) throws Exception{
	
		String query = "update login set active = false where username = ?";
		conn=ds.getConnection();
		
		try {
			
			//check if username exists
			LoginDao dao = new LoginDao();
			Login check = dao.getLoginPassword(username);
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			pstmt.executeUpdate();
			
			}
		catch (UserNotFoundException e) {
			
			throw new UserNotFoundException();
		}
	
		catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally {
			conn.close();
		}
		return true;
	
	}
	
	public  boolean deleteRecordHard(String username) throws Exception{
		
		String query = "delete from login where username = ?";
		conn=ds.getConnection();
		
		try {
			
			//check if username exists
			LoginDao dao = new LoginDao();
			Login check = dao.getLoginPassword(username);
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			pstmt.executeUpdate();
			}
		
		catch(UserNotFoundException e) {
			throw new UserNotFoundException();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally {
			conn.close();
		}
		return true;
	
	}
	
//	
//	public  boolean updateRecords(String username) throws Exception{
//		
//		String query = "update login set active = false where username = ?";
//		conn=ds.getConnection();
//		
//		try {
//			
//			//check if username exists
//			LoginDao dao = new LoginDao();
//			Login check = dao.getLoginPassword(username);
//			
//			PreparedStatement pstmt = conn.prepareStatement(query);
//
//			pstmt.setString(1, username);
//			if(pstmt.executeUpdate()<=0) {
//				throw new Exception("Username does not exist");
//			}
//			}
//		catch (SQLException e) {
//			System.out.println("SQLException: "+e.getMessage());
//			e.printStackTrace();
//		}
//		finally {
//			conn.close();
//		}
//		return true;
//	
//	}
	
}


	
	
	

