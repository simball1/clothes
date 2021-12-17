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
		
		//생년월일 처리
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day= request.getParameter("day");
		
		if(month.length() == 1) {
			month = "0" + month;
		}
		
		if(day.length() == 1) {
			day = "0" + day;
		}
		
		String birth = year + month + day;
		
		//수정할 회원 정보
		LogonVO member = new LogonVO();
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		member.setBirth(birth);
		
		//수정할 회원 정보를 가지고 수정 처리 후 성공여부 반환
		LogonDAO manager = LogonDAO.getInstance();
		int check = manager.updateMember(member);
		
		request.setAttribute("check", new Integer(check));
		return "/member/modifyPro.jsp";
	}

}
