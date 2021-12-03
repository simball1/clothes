package manager.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;

public class ClothesListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<ManagerVO> clothesList = null;
		String clothes_kind = request.getParameter("clothes_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		ManagerDAO clothesProcess = ManagerDAO.getInstance();
		count = clothesProcess.getClothesCount();
		
		if(count > 0) {//상품이 있으면 수행
			//상품전체를 테이블에서 얻어내서 clothesList에 저장
			clothesList = clothesProcess.getClothess(clothes_kind);
			//clothesList를 뷰에서 사용할 수 있도록 request속성에 저장
			request.setAttribute("clothesList", clothesList);
		}
		
		//뷰에서 사용할 속성
		request.setAttribute("count", new Integer(count));
		request.setAttribute("clothes_kind", clothes_kind);
		request.setAttribute("type", new Integer(0));
		return "/manager/productProcess/clothesList.jsp";
	}
	
	
}
