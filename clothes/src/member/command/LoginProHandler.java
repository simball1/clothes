package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;

public class LoginProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd  = request.getParameter("passwd");

		//사용자가 입력한 id, passwd를 가지고 인증 체크 후 값 반환
		LogonDAO manager = LogonDAO.getInstance();
		int check= manager.userCheck(id,passwd);
		
		request.setAttribute("id", id);
		request.setAttribute("check", new Integer(check));
		return "/member/loginPro.jsp";
	}

}
