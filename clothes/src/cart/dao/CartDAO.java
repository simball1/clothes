package cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cart.dto.CartVO;


public class CartDAO {

	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	private CartDAO() {}
	
	private Connection getConnection() throws Exception{
		 Context initCtx = new InitialContext();
	        Context envCtx = (Context) initCtx.lookup("java:comp/env");
	        DataSource ds = (DataSource)envCtx.lookup("jdbc/clothesshop");
	        return ds.getConnection();
	}

	//[장바구니] 누르면 수행 cart 테이블에 새로 추가
	public void insertCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement  pstmt = null;
		String sql="";
	
		try {
			conn = getConnection();
			sql = "insert into cart (clothes_id, buyer," +
				  "clothes_title,buy_price,buy_count,clothes_size,clothes_image)" +
				  "values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1,  cart.getClothes_id());
			pstmt.setString(2, cart.getBuyer());
			pstmt.setString(3, cart.getClothes_title());
			pstmt.setInt(4, cart.getBuy_price());
			pstmt.setByte(5, cart.getBuy_count());
			pstmt.setString(6, cart.getClothes_size());
			pstmt.setString(7, cart.getClothes_image());
		
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null)
				try {pstmt.close();} catch(SQLException ex) {}
			if(conn != null)
				try {conn.close();} catch(SQLException ex) {}
		}
	
	}
//id에 해당하는 레코드의 수를 얻어내는 메소드
public int getListCount(String id) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int x=0;
	
	try {
		conn= getConnection();
		
		pstmt = conn.prepareStatement(
				"select count(*) from cart where buyer=?");
				
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			x = rs.getInt(1);
		}
	}catch(Exception ex) {
		ex.printStackTrace();
} finally {
	if(rs !=null)
		try {rs.close();} catch(SQLException ex) {}
	if(pstmt !=null)
		try {pstmt.close();} catch(SQLException ex) {}
	if(conn !=null)
		try {conn.close();} catch(SQLException ex) {}
}
	return x;
}

//id에 해당하는 레코드의 목록을 얻어내는 메소드
public List<CartVO> getCart(String id, int count) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CartVO cart = null;
	String sql ="";
	List<CartVO> lists = null;
	
	try {
		conn = getConnection();
		
		sql = "select * from cart where buyer =?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		lists = new ArrayList<CartVO>(count);
		
		while(rs.next()) {
			cart = new CartVO();
			
			cart.setCart_id(rs.getInt("cart_id"));
			cart.setClothes_id(rs.getInt("clothes_id"));
			cart.setClothes_title(rs.getString("clothes_title"));
			cart.setBuy_price(rs.getInt("buy_price"));
			cart.setBuy_count(rs.getByte("buy_count"));
			cart.setClothes_size(rs.getString("clothes_size"));
			cart.setClothes_image(rs.getString("clothes_image"));
		
			lists.add(cart);
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		if(rs != null)
			try {rs.close();} catch(SQLException ex) {}
		if(pstmt != null)
			try {pstmt.close();} catch(SQLException ex) {}
		if(conn != null)
			try {conn.close();} catch(SQLException ex) {}
	}
	
	return lists;
}

//장바구니 수량 수정

public void updateCount(int cart_id, byte count)throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
			
	try {
		conn= getConnection();
		
		pstmt = conn.prepareStatement(
				"update cart set buy_count=? where cart_id =?");
		pstmt.setByte(1,  count);
		pstmt.setInt(2, cart_id);
		
		pstmt.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		if (pstmt != null)
			try {pstmt.close();} catch(SQLException ex) {}
		if (conn != null)
			try {conn.close();} catch(SQLException ex) {}
		
	}
}

//장바구니에서 cart_id 에 대한 레코드 삭제

public void delectList(int cart_id) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		pstmt = conn.prepareStatement(
				"delete from cart where cart_id=?");
		pstmt.setInt(1,  cart_id);
		
		pstmt.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
				
	}finally {
		if(pstmt != null)
			try {pstmt.close();} catch(SQLException ex) {}
		if(conn != null)
			try {conn.close();} catch(SQLException ex) {}
	}
}

 // id에 해당하는 모든 레코드를 삭제하는 메소드 : 장바구니 비우기 버튼
public void deleteAll(String id) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		pstmt = conn.prepareStatement(
				"delete from cart where buyer=?");
		pstmt.setString(1, id);
		
		pstmt.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		if(pstmt != null)
			try {pstmt.close(); } catch(SQLException ex) {}
		if(conn != null)
			try {conn.close(); } catch(SQLException ex) {}
	}
}
}













