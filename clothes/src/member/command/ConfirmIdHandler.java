package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;

public class ConfirmIdHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		  
		//주어진 id의 중복여부를 체크해 값을 반환.
		LogonDAO manager = LogonDAO.getInstance();
		int check= manager.confirmId(id);
		
		request.setAttribute("check", new Integer(check));
		return "/member/confirmId.jsp";
	}

}
