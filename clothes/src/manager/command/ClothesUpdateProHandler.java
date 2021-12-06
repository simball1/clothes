package manager.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;

public class ClothesUpdateProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//한글 인코딩
		
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/clothesImage"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		int maxSize = 1*1024*1024;  //최대 업로될 파일크기 1Mb
		
		MultipartRequest imageUp = null;

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder); 
		
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  
		     while(files.hasMoreElements()){
		       String name = (String)files.nextElement();
		       filename = imageUp.getFilesystemName(name);
		     }
		}catch(Exception e){
		     e.printStackTrace();
		}
		
		ManagerVO clothes = new ManagerVO();
		int clothes_id= Integer.parseInt( imageUp.getParameter("clothes_id"));
		String clothes_kind = imageUp.getParameter("clothes_kind");
		String clothes_title = imageUp.getParameter("clothes_title");
		String clothes_price = imageUp.getParameter("clothes_price");
		String clothes_count = imageUp.getParameter("clothes_count");
		String clothes_content = imageUp.getParameter("clothes_content");
		
		clothes.setClothes_kind(clothes_kind);
		clothes.setClothes_title(clothes_title);
		clothes.setClothes_price(Integer.parseInt(clothes_price));
		clothes.setClothes_count(Short.parseShort(clothes_count));
		clothes.setClothes_image(filename);
		clothes.setClothes_content(clothes_content);
		clothes.setReg_date(new Timestamp(System.currentTimeMillis()));
		

		//DB연동해서 상품 수정 처리
		ManagerDAO clothesProcess = ManagerDAO.getInstance();
		clothesProcess.updateClothes(clothes, clothes_id);
		
		request.setAttribute("clothes_kind", clothes_kind);
		return "/manager/productProcess/clothesUpdatePro.jsp";
		
		
	}

	
}
