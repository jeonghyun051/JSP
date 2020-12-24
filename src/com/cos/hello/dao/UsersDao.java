package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

public class UsersDao {

	//dao에서는 단순하게 하나만 실행하는 함수를 만드는것
	public int insert(Users user) {	//String username, String password, String email 다쓰지말고 빈즈해놓고 한번에
		//2번 DB에 연결해서 3가지 값을 insert 하기 !생략!
		StringBuffer sb = new StringBuffer();	//String전용 컬렉션으로 담기(동기화) 
		sb.append("INSERT INTO users(username, password, email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		//sql += "VALUES(?,?,?)";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			int result = pstmt.executeUpdate();	//변경된 행의 개수를 리턴dml은 다 업데이트
			
			return result;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
}
