package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.dto.BoardVO;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	//.jsp 페이지에서 DB 연동빈인 BoardDAO 클래스의 메소드에 접근 시 필요
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private BoardDAO() {}
	
	//커넥션 풀로부터 Connection 객체를 얻어냄 : DB 연동빈 쿼리문을 수행하는 메소드에서 사용
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
	    DataSource ds = (DataSource)envCtx.lookup("jdbc/clothesshop");
	    return ds.getConnection();
	}
		
	
}
