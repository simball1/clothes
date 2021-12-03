package manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;

public class ClothesUpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int clothes_id = Integer.parseInt(request.getParameter("clothes_id"));
		String clothes_kind = request.getParameter("clothes_kind");
		
		//DB연동 clothes_id에 해당하는 상품을 얻어내서 clothes에 저장
		ManagerDAO clothesProcess = ManagerDAO.getInstance();
		ManagerVO clothes = clothesProcess.getClothes(clothes_id);
		
		request.setAttribute("clothes_id", clothes_id);
		request.setAttribute("clothes_kind", clothes_kind);
		request.setAttribute("clothes", clothes);
		request.setAttribute("type", new Integer(0));
		return "/manager/productProcess/clothesUpdateForm.jsp";

	}
	
	
	
}
