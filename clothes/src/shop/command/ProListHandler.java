package shop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;

public class ProListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
				List<ManagerVO> clothesList = null;
				int count = 0;
				String clothes_kind = request.getParameter("clothes_kind");
				
				ManagerDAO clothesProcess = ManagerDAO.getInstance();
				
				//kind값이 all이면 전체 상품의 수를 얻어냄
				if(clothes_kind.equals("all"))
		            count = clothesProcess.getClothesCount(); 
				else//all이 아니면 해당 카테고리의 상품수를 얻어냄
					count = clothesProcess.getClothesCount(clothes_kind);
				
		        if (count > 0){//상품이 있으면 수행
		        	//상품목록을 얻어냄
		        	clothesList = clothesProcess.getClothess(clothes_kind);
		        	request.setAttribute("clothesList", clothesList);
		        }
		        
		        //해당 뷰에서 사용할 속성
		        request.setAttribute("count", new Integer(count));
		        request.setAttribute("clothes_kind", clothes_kind);
		        request.setAttribute("type", new Integer(1));
				return "/shop/showList.jsp";
			}
}
