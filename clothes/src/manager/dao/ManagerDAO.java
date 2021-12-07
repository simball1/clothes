package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import manager.dto.ManagerVO;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class ManagerDAO {
	//ManagerDAO 전역 객체 생성 <- 한개의 객체만 생성해서 공유(싱글톤)
	private static ManagerDAO instance = new ManagerDAO();
	
	//ManagerDAO객체를 리턴하는 메소드
	public static ManagerDAO getInstance() {
		return instance;
	}
	
	private ManagerDAO() {}
	
	//커넥션 풀에서 커넥션 객체를 얻어내는 메소드
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/clothesshop");
        return ds.getConnection();
	}
	
	//관리자 인증 메소드
    public int userCheck(String id, String passwd){
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs= null;
		int x=-1;
        
		SHA256 sha = SHA256.getInsatnce();
		try {
            conn = getConnection();
            
            String orgPass = passwd;
            String shaPass = sha.getSha256(orgPass.getBytes());
        	
            pstmt = conn.prepareStatement(
              "select managerPasswd from manager where managerId = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

			if(rs.next()){//해당 아이디가 있으면 수행
				String dbpasswd= rs.getString("managerPasswd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd))
					x= 1; //인증성공
				else
					x= 0; //비밀번호 틀림
			}else//해당 아이디 없으면 수행
				x= -1;//아이디 없음
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
	}
    
    //옷 등록 메소드
    public void insertClothes(ManagerVO clothes) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
        	conn = getConnection();
        	String sql = "insert into clothes(clothes_kind, clothes_title, clothes_price,";
        	sql += "clothes_count, clothes_size, clothes_image, clothes_content, reg_date) values (?,?,?,?,?,?,?,?) ";
        	
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, clothes.getClothes_kind());
        	pstmt.setString(2, clothes.getClothes_title());
        	pstmt.setInt(3, clothes.getClothes_price());
        	pstmt.setShort(4, clothes.getClothes_count());
        	pstmt.setString(5, clothes.getClothes_size());
        	pstmt.setString(6, clothes.getClothes_image());
        	pstmt.setString(7, clothes.getClothes_content());
        	pstmt.setTimestamp(8, clothes.getReg_date());
        	
        	pstmt.executeUpdate();
           
        } catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    // 전체등록된 옷의 수를 얻어내는 메소드
    public int getClothesCount() throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;
        
        try {
        	conn = getConnection();
            
            pstmt = conn.prepareStatement("select count(*) from clothes");
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getInt(1);
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
    
 // 해당 분류의 옷의 수를 얻어내는 메소드
 	public int getClothesCount(String clothes_kind)
 	throws Exception {
 	    Connection conn = null;
 	    PreparedStatement pstmt = null;
 	    ResultSet rs = null;

 	    int x=0;
 	    int kind  = Integer.parseInt(clothes_kind);

 	    try {
 	        conn = getConnection();
 	        String query = "select count(*) from clothes where clothes_kind=" + kind;
 	        pstmt = conn.prepareStatement(query);
 	        rs = pstmt.executeQuery();

 	        if (rs.next()) 
 	            x= rs.getInt(1);
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
 	
 	//옷의 이름을 얻어냄
 		public String getClothesTitle(int clothes_id){
 	        Connection conn = null;
 	        PreparedStatement pstmt = null;
 	        ResultSet rs = null;
 	        String x="";

 	        try {
 	            conn = getConnection();
 	            
 	            pstmt = conn.prepareStatement("select clothes_title from clothes where clothes_id = "+clothes_id);
 	            rs = pstmt.executeQuery();

 	            if (rs.next()) 
 	               x= rs.getString(1);
 	        } catch(Exception ex) {
 	            ex.printStackTrace();
 	        } finally {
 	            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
 	            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
 	            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
 	        }
 			return x;
 	    }
    
    // 분류별 또는 전체등록된 옷의 정보를 얻어내는 메소드
    public List<ManagerVO> getClothess(String clothes_kind) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ManagerVO> clothesList=null;
        
        try {
        	conn = getConnection();
        	
        	String sql1 = "select * from clothes";
        	String sql2 = "select * from clothes ";
        	sql2 += "where clothes_kind = ? order by reg_date desc";
        	
        	if(clothes_kind.equals("all")||clothes_kind.equals("")) {
        		pstmt = conn.prepareStatement(sql1);
        	} else {
        		pstmt = conn.prepareStatement(sql2);
        		pstmt.setString(1, clothes_kind);
        	}
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()) {
        		clothesList = new ArrayList<ManagerVO>();
        		do {
        			ManagerVO clothes = new ManagerVO();
        			
        			clothes.setClothes_id(rs.getInt("clothes_id"));
        			clothes.setClothes_kind(rs.getString("clothes_kind"));
        			clothes.setClothes_title(rs.getString("clothes_title"));
        			clothes.setClothes_price(rs.getInt("clothes_price"));
        			clothes.setClothes_count(rs.getShort("clothes_count"));
        			clothes.setClothes_size(rs.getString("clothes_size"));
        			clothes.setClothes_image(rs.getString("clothes_image"));
        			clothes.setReg_date(rs.getTimestamp("reg_date"));
        			
        			clothesList.add(clothes);
        			
        		}while(rs.next());
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
		return clothesList;
    }
    
 // 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신규 옷 목록을 얻어내는 메소드
 	public ManagerVO[] getClothess(String clothes_kind,int count)
     throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         ManagerVO clothesList[]=null;
         int i=0;
         
         try {
             conn = getConnection();
             
             String sql = "select * from clothes where clothes_kind = ? ";
             sql += "order by reg_date desc limit ?,?";
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, clothes_kind);
             pstmt.setInt(2, 0);
             pstmt.setInt(3, count);
         	rs = pstmt.executeQuery();

             if (rs.next()) {
            	 clothesList = new ManagerVO[count];
                 do{
                	 ManagerVO clothes= new ManagerVO();
                	 clothes.setClothes_id(rs.getInt("clothes_id"));
                	 clothes.setClothes_kind(rs.getString("clothes_kind"));
                	 clothes.setClothes_title(rs.getString("clothes_title"));
                	 clothes.setClothes_price(rs.getInt("clothes_price"));
                	 clothes.setClothes_count(rs.getShort("clothes_count"));
                	 clothes.setClothes_size(rs.getString("clothes_size"));
                	 clothes.setClothes_image(rs.getString("clothes_image"));
                	 clothes.setReg_date(rs.getTimestamp("reg_date"));
                	 
                     clothesList[i]=clothes;
                	
                     i++;
 			    }while(rs.next());
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
 		return clothesList;
     }
    
    //clothesId에 해당하는 옷의 정보를 얻어내는 메소드
    //등록된 옷을 수정하기 위해 수정폼으로 읽어들이기 위한 메소드
    public ManagerVO getClothes(int clothesId) throws Exception {
    	  Connection conn = null;
          PreparedStatement pstmt = null;
          ResultSet rs = null;
          ManagerVO clothes = null;
          
          try {
        	  conn = getConnection();
              
              pstmt = conn.prepareStatement(
              	"select * from clothes where clothes_id = ?");
              pstmt.setInt(1, clothesId);
              
              rs = pstmt.executeQuery();
              
              if(rs.next()) {
            	  clothes = new ManagerVO();
            	  
            	  clothes.setClothes_kind(rs.getString("clothes_kind"));
            	  clothes.setClothes_title(rs.getString("clothes_title"));
            	  clothes.setClothes_price(rs.getInt("clothes_price"));
            	  clothes.setClothes_count(rs.getShort("clothes_count"));
            	  clothes.setClothes_size(rs.getString("clothes_size"));
            	  clothes.setClothes_image(rs.getString("clothes_image"));
            	  clothes.setClothes_content(rs.getString("clothes_content"));
         	  
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
          return clothes;
    }
    
    // 등록된 옷의 정보를 수정시 사용하는 메소드
    public void updateClothes(ManagerVO clothes, int clothesId)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql;
        
        try {
            conn = getConnection();
            
            sql = "update clothes set clothes_kind=?,clothes_title=?,clothes_price=?";
            sql += ",clothes_count=?";
            sql += ",clothes_image=?,clothes_content=?";
            sql += " where clothes_id=?";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, clothes.getClothes_kind());
            pstmt.setString(2, clothes.getClothes_title());
            pstmt.setInt(3, clothes.getClothes_price());
            pstmt.setShort(4, clothes.getClothes_count());
			pstmt.setString(5, clothes.getClothes_image());
			pstmt.setString(6, clothes.getClothes_content());
			pstmt.setInt(7, clothesId);
            
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
 // clothesId에 해당하는 옷의 정보를 삭제시 사용하는 메소드
    public void deleteClothes(int clothesId)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        try {
			conn = getConnection();

            pstmt = conn.prepareStatement(
            	"delete from clothes where clothes_id=?");
            pstmt.setInt(1, clothesId);
            
            pstmt.executeUpdate();
            
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
    }
}
