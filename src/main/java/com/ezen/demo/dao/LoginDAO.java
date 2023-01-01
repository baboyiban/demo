package com.ezen.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.User;

@Repository
public class LoginDAO {
	// field
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// private
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void closeAll() {
		try {
			if (this.conn != null)
				this.conn.close();
			if (this.pstmt != null)
				this.pstmt.close();
			if (this.rs != null)
				this.rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// public
	public boolean login(User user) {
		getConn();
		try {
			String sql = "SELECT * FROM user_table WHERE id=? AND pwd=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getId());
			this.pstmt.setString(2, user.getPwd());
			this.rs = this.pstmt.executeQuery();
			return this.rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
}
