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
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
        member.setReg_date(new Timestamp(System.currentTimeMillis()));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		
		//회원가입처리
        LogonDAO dbPro = LogonDAO.getInstance();
        dbPro.insertMember(member);
		
		return "/member/registerPro.jsp";
	}

}
