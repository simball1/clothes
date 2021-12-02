package manager.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;

public class ManagerLoginProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글 인코딩
		
		//넘어온 요청의 데이터를 얻어냄
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//DB와 연동해서 사용자의 인증을 처리
		ManagerDAO daoPro = ManagerDAO.getInstance();
		int check = daoPro.userCheck(id, passwd);	// 1 = 인증성공, 0 = 비밀번호 틀림, -1 = 아이디 없음
		
		//해당 뷰(응답페이지)로 보낼 내용을 request속성에 지정
		request.setAttribute("check", new Integer(check));
		request.setAttribute("id", id);
		
		return "/manager/logon/mLoginPro.jsp";
	}
	
	
}
