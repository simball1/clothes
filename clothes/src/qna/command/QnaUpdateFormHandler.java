package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaUpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		String clothes_kind = request.getParameter("clothes_kind");

		//수정할 qna를 테이블에서 가져옴
		QnaDAO qnaProcess = QnaDAO.getInstance(); 
		QnaVO qna  =  qnaProcess.updateGetArticle(qna_id);
		
		request.setAttribute("qna", qna);
		request.setAttribute("qna_id", new Integer(qna_id));
		request.setAttribute("clothes_kind", clothes_kind);
		request.setAttribute("type", new Integer(1));
		return "/qna/qnaUpdateForm.jsp";
	}

}
