package member.command;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.LogonDAO;
import member.dto.LogonVO;

public class RegisterProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//회원가입 정보
		LogonVO member = new LogonVO();
		
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
		
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
        member.setReg_date(new Timestamp(System.currentTimeMillis()));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		member.setBirth(birth);
		
		//회원가입처리
        LogonDAO dbPro = LogonDAO.getInstance();
        dbPro.insertMember(member);
		
		return "/member/registerPro.jsp";
	}

}
