package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.hello.config.DBConn;
import com.cos.hello.dto.JoinDto;
import com.cos.hello.dto.LoginDto;
import com.cos.hello.model.Users;

public class UsersDao {
	
	public Users selectById(int id) {
		StringBuffer sb = new StringBuffer();	//String전용 컬렉션으로 담기(동기화) 
		String sql = ("SELECT id, password, username, email FROM users WHERE id = ?");

		//sql += "VALUES(?,?,?)";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			//int result = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.password(rs.getString("password"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
						
				return userEntity;
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	//dao에서는 단순하게 하나만 실행하는 함수를 만드는것
	public int insert(JoinDto joinDto) {	//String username, String password, String email 다쓰지말고 빈즈해놓고 한번에
		//2번 DB에 연결해서 3가지 값을 insert 하기 !생략!
		StringBuffer sb = new StringBuffer();	//String전용 컬렉션으로 담기(동기화) 
		sb.append("INSERT INTO users(username, password, email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		//sql += "VALUES(?,?,?)";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinDto.getUsername());
			pstmt.setString(2, joinDto.getPassword());
			pstmt.setString(3, joinDto.getEmail());
			int result = pstmt.executeUpdate();	//변경된 행의 개수를 리턴 dml은 다 업데이트
			
			return result;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
	
	public Users login(LoginDto loginDto) {
		StringBuffer sb = new StringBuffer();	//String전용 컬렉션으로 담기(동기화) 
		String sql = ("SELECT id, username, email FROM users WHERE username = ? AND password = ? ");

		//sql += "VALUES(?,?,?)";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginDto.getUsername());
			pstmt.setString(2, loginDto.getPassword());
			//int result = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
						
				return userEntity;
				
			}
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	public int update(Users user) {
 
		StringBuffer sb = new StringBuffer(); 
		sb.append("UPDATE users SET password = ?, email = ? ");
		sb.append("WHERE id = ?");
		String sql = sb.toString();
		//String sql = ("UPDATE users SET password = ? , email = ? where id = ?");

		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate();
		
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	

	public int delete(int id) {
		
		String sql = ("DELETE FROM users where id = ?");

		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
