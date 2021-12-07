package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;
import member.dto.LogonVO;

public class ModifyProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
request.setCharacterEncoding("utf-8");
		
		//수정할 회원 정보
		LogonVO member = new LogonVO();
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		
		//수정할 회원 정보를 가지고 수정 처리 후 성공여부 반환
		LogonDAO manager = LogonDAO.getInstance();
		int check = manager.updateMember(member);
		
		request.setAttribute("check", new Integer(check));
		return "/member/modifyPro.jsp";
	}

}
