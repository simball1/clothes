package manager.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaReplyProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
        //상품Qna 답변글 관련내용
        int qna_id =  Integer.parseInt(request.getParameter("qna_id"));
        int clothes_id =  Integer.parseInt(request.getParameter("clothes_id"));
        String qna_writer =  request.getParameter("qna_writer");
		String clothes_title =  request.getParameter("clothes_title");
		String qna_content = "[답변]:"+request.getParameter("qna_content");
		Byte qora =  Byte.parseByte(request.getParameter("qora"));
		byte reply = 1;//답변여부- 답변함
		
		//상품Qna 답변글 저장을 위한 정보 설정
		QnaVO qna = new QnaVO();
		qna.setQna_id(qna_id);
		qna.setClothes_id(clothes_id);
		qna.setClothes_title(clothes_title);
		qna.setQna_content(qna_content);
        qna.setQna_writer(qna_writer);
        qna.setGroup_id(qna_id);
        qna.setReply(reply);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		//DB작업 - 테이블에 상품Qna 답변글 추가
		QnaDAO qnaProcess = QnaDAO.getInstance();
		int check = qnaProcess.insertArticle(qna, qna_id);
		
		request.setAttribute("check", new Integer(check));
		return "/manager/qnaProcess/qnaReplyPro.jsp";
	}

}
