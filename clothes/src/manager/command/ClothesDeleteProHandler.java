package manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;

public class ClothesDeleteProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int clothes_id = Integer.parseInt(request.getParameter("clothes_id"));
		String clothes_kind = request.getParameter("clothes_kind");
		
		//DB연동 - book_id에 해당하는 상품을 삭제
		ManagerDAO clothesProcess = ManagerDAO.getInstance();
		clothesProcess.deleteClothes(clothes_id); 
		
		request.setAttribute("clothes_kind", clothes_kind);
		return "/manager/productProcess/clothesDeletePro.jsp";
	}
	
	
}
