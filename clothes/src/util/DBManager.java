package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	// MemberDAO에 있는 getConnection()를 별도의 파일 DBManager로 공통화 시킴
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/clothesshop");
			conn = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 자원반납하는 부분을 별도의 메소드로 빼냄(공통화)
	// select을 수행한 후 리소스 해제를 위한 메소드
	// try-catch-finally 중에 finally에서 수행한 자원반납 과정을 공통 파일로 뽑아냄
	// PrepareStatement와 Statement랑 비슷해서 하나만 존재
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {	e.printStackTrace();	}
	}

	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {	e.printStackTrace();	}
	}

	// 필요하면 추가해 준다. (try-with-resource를 사용해서 connection, statement은 자동 반납되므로
	// ResultSet만 반납
	public static void close(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}