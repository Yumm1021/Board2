package com.koreait.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
	public static Connection getCon() throws Exception {
		final String URL = "jdbc:mysql://localhost:3303/javaboard1?serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "koreait2020";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("연결 이상무");
		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) { // 똑같은 메서드 두개 만드는 거 >> 오버로딩 , 부모꺼 갖다 쓰기 >> 오버라이딩
		if(rs != null) {
			try { rs.close(); } catch(Exception e) {}
		}
		close(con, ps);
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		if(ps != null) {
			try { ps.close(); } catch(Exception e) {}
		}
		
		if(con != null) {
			try { ps.close(); } catch(Exception e) {}
		}
	}
}
