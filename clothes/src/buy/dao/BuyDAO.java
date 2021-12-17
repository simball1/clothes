package buy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import buy.dto.BuyVO;
import cart.dto.CartVO;

public class BuyDAO {

    private static BuyDAO instance = new BuyDAO();
    
    public static BuyDAO getInstance() {
    	return instance;
    }
    
    private BuyDAO() {}
    
    private Connection getConnection() throws Exception{
    	  Context initCtx = new InitialContext();
          Context envCtx = (Context) initCtx.lookup("java:comp/env");
          DataSource ds = (DataSource)envCtx.lookup("jdbc/clothesshop");
          return ds.getConnection();
      }
/*  
 // bank테이블에 있는 전체 레코드를 얻어내는 메소드(삭제??)
    public List<String> getAccount(){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> accountList = null;
        try {
            conn = getConnection();
                        
            pstmt = conn.prepareStatement("select * from bank");
            rs = pstmt.executeQuery();
            
            accountList = new ArrayList<String>();
            
            while (rs.next()) {
           	  String account = new String(rs.getString("account")+" "
                     + rs.getString("bank")+" "+rs.getString("name"));
           	  accountList.add(account);
		    }
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
        return accountList;
    }
*/
 
    @SuppressWarnings("resource")
    // 구매 테이블 buy에 구매목록 등록
    public void insertBuy(List<CartVO> lists, String id, String deliveryName, String deliveryTel, String deliveryAddress) throws Exception {
        	Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Timestamp reg_date = null;
            String sql = "";
            String maxDate =" ";
            String number = "";
            String todayDate = "";
            String compareDate = "";
            long buyId = 0; 
            short nowCount ;
    		try {
    			conn = getConnection();
    			reg_date = new Timestamp(System.currentTimeMillis());
    			todayDate = reg_date.toString();
                compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);
    		
    		
                pstmt = conn.prepareStatement("select max(buy_id) from buy");

                rs = pstmt.executeQuery();
                rs.next();
                if (rs.getLong(1) > 0){         
                	Long val = new Long(rs.getLong(1));
                    maxDate = val.toString().substring(0, 8);
                    number =  val.toString().substring(8);
                    if(compareDate.equals(maxDate)){
                    	if((Integer.parseInt(number)+1)<10000)
                    	    buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1+10000));
                    	else
                    		buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1));
                    }else{
                    	compareDate += "00001";
            		    buyId = Long.parseLong(compareDate);
                    }
                }else {
                	compareDate += "00001";
        		    buyId = Long.parseLong(compareDate);
                }
              //105~154라인까지 하나의 트랜잭션으로 처리
                conn.setAutoCommit(false);
                for(int i=0; i<lists.size();i++){
                	//해당 아이디에 대한 cart테이블 레코드를을 가져온후 buy테이블에 추가
                	CartVO cart = lists.get(i);
                	
                	sql = "insert into buy (buy_id,buyer,clothes_id,clothes_title,buy_price,buy_count, clothes_size,";
                    sql += "clothes_image,buy_date,deliveryName,deliveryTel,deliveryAddress)";
                    sql += " values (?,?,?,?,?,?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql);
                
                    pstmt.setLong(1, buyId);
                    pstmt.setString(2, id);
                    pstmt.setInt(3, cart.getClothes_id());
                    pstmt.setString(4, cart.getClothes_title());
                    pstmt.setInt(5, cart.getBuy_price());
                    pstmt.setByte(6, cart.getBuy_count());
                    pstmt.setNString(7, cart.getClothes_size());
                    pstmt.setString(8, cart.getClothes_image());
                    pstmt.setTimestamp(9, reg_date);
                    pstmt.setString(10, deliveryName);
                    pstmt.setString(11, deliveryTel);
                    pstmt.setString(12, deliveryAddress);
                    pstmt.executeUpdate();
                    
                    //상품이 구매되었으므로 clothes테이블의 상품수량을 재조정함
                    pstmt = conn.prepareStatement(
                    		"select clothes_count from clothes where clothes_id=?");
                    pstmt.setInt(1, cart.getClothes_id());
                    rs = pstmt.executeQuery();
                    rs.next();
                    
                    nowCount = (short)(rs.getShort(1) - 1);
                    
                    sql = "update clothes set clothes_count=? where clothes_id=?";
                    pstmt = conn.prepareStatement(sql);
               
                    pstmt.setShort(1, nowCount);
        			pstmt.setInt(2, cart.getClothes_id());
                    
                    pstmt.executeUpdate(); 
                }
                pstmt = conn.prepareStatement(
                        "delete from cart where buyer=?");
                      pstmt.setString(1, id);
                    
                      pstmt.executeUpdate();
                      
                      conn.commit();
                      conn.setAutoCommit(true);
                  }catch(Exception ex) {
                  	ex.printStackTrace();
                  } finally {
                      if (pstmt != null) 
                      	try { pstmt.close(); } catch(SQLException ex) {}
                      if (conn != null) 
                      	try { conn.close(); } catch(SQLException ex) {}
                  }
              }
    
    
    
    //id에 해당하는 buy테이블의 레코드수를 얻어내는 메소드
    public int getListCount(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select count(*) from buy where buyer=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
  //buy테이블의 전체 레코드수를 얻어내는 메소드
    public int getListCount() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            		"select count(*) from buy");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
    
    //id에 해당하는 buy테이블의 구매목록을 얻어내는 메소드
    public List<BuyVO> getBuyList(String id) throws Exception {
   	    Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BuyVO buy=null;
        String sql = "";
        List<BuyVO> lists = null;
        
        try {
       	    conn = getConnection();
       	 
       	    sql = "select * from buy where buyer = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            lists = new ArrayList<BuyVO>();

            while (rs.next()) {
              buy = new BuyVO();
           	 
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setClothes_id(rs.getInt("clothes_id"));
           	  buy.setClothes_title(rs.getString("clothes_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count")); 
           	  buy.setClothes_size(rs.getString("clothes_size"));
           	  buy.setClothes_image(rs.getString("clothes_image"));
           	 
           	  lists.add(buy);
			}
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return lists;
    }
  //buy테이블의 전체 목록을 얻어내는 메소드
    public List<BuyVO> getBuyList() throws Exception {
   	    Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BuyVO buy=null;
        String sql = "";
        List<BuyVO> lists = null;
        
        try {
       	 conn = getConnection();
       	 
       	 sql = "select * from buy";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            lists = new ArrayList<BuyVO>();

            while (rs.next()) {
              buy = new BuyVO();
           	 
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setBuyer(rs.getString("buyer"));
           	  buy.setClothes_id(rs.getInt("clothes_id"));
           	  buy.setClothes_title(rs.getString("clothes_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count"));
           	  buy.setClothes_size(rs.getString("clothes_size"));
           	  buy.setClothes_image(rs.getString("clothes_image"));
           	  buy.setBuy_date(rs.getTimestamp("buy_date"));
           	  buy.setDeliveryName(rs.getString("deliveryName"));
           	  buy.setDeliveryTel(rs.getString("deliveryTel"));
           	  buy.setDeliveryAddress(rs.getString("deliveryAddress"));
           	 
           	  lists.add(buy);
		    }
    
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return lists;
    }
}  