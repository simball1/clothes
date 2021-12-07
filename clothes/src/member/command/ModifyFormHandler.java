package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;
import member.dto.LogonVO;

public class ModifyFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		//회원 정보를 수정하기 위한 정보를 얻어냄
		LogonDAO manager = LogonDAO .getInstance();
		LogonVO m = manager.getMember(id,passwd); 
		
		request.setAttribute("m",m);
		request.setAttribute("id", id);
		request.setAttribute("type", new Integer(1));
		return "/member/modifyForm.jsp";
	}

}
