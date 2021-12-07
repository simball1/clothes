package qna.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
request.setCharacterEncoding("utf-8");
		
		//폼에서 입력 후 넘어온 qna 내용
		String qna_writer =  request.getParameter("qna_writer");
		String clothes_title =  request.getParameter("clothes_title");
		String qna_content =  request.getParameter("qna_content");
		int clothes_id =  Integer.parseInt(request.getParameter("clothes_id"));
		Byte qora =  Byte.parseByte(request.getParameter("qora"));
		byte reply = 0; //답변여부 - 미답변
		
		//qna를 추가하기 위한 정보작성
		QnaVO qna = new QnaVO();
		qna.setClothes_id(clothes_id);
		qna.setClothes_title(clothes_title);
		qna.setQna_content(qna_content);
        qna.setQna_writer(qna_writer);
        qna.setReply(reply);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		//qna를 테이블에 추가
		QnaDAO qnaProcess = QnaDAO.getInstance();
		int check = qnaProcess.insertArticle(qna);
		
		request.setAttribute("check", new Integer(check));
		return "/qna/qnaPro.jsp";
	}

}
