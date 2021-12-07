package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;

public class QnaFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String clothes_kind = request.getParameter("clothes_kind");
		int clothes_id=Integer.parseInt(request.getParameter("clothes_id"));  
		
		//clothes_id에 해당하는 book_title을 얻어냄
		ManagerDAO clothesProcess = ManagerDAO.getInstance();
		String clothes_title = clothesProcess.getClothesTitle(clothes_id);
	    
		request.setAttribute("clothes_kind", clothes_kind);
	    request.setAttribute("clothes_id", new Integer(clothes_id));
	    request.setAttribute("clothes_title", clothes_title);
	    request.setAttribute("qora", new Integer(1));
		request.setAttribute("type", new Integer(1));
		return "/qna/qnaForm.jsp";
	}

}
