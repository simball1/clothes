package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;

public class DeleteProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String passwd  = request.getParameter("passwd");
		
		//사용자가 입력한 id, passwd를 가지고 회원정보 삭제 후 성공여부 반환
		LogonDAO manager = LogonDAO.getInstance();
		int check = manager.deleteMember(id,passwd);
		
		request.setAttribute("check", new Integer(check));  
		return "/member/deletePro.jsp";
	}

}
