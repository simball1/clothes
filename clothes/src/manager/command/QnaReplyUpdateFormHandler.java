package manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaReplyUpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//주어진 qna_id에 해당하는 수정할 qna답변을 가져옴 
		QnaDAO qnaProcess = QnaDAO.getInstance();
		QnaVO qna  =  qnaProcess.updateGetArticle(qna_id);
		
		request.setAttribute("qna", qna);
		request.setAttribute("qna_id", new Integer(qna_id));
		request.setAttribute("type", new Integer(0));
		return "/manager/qnaProcess/qnaReplyUpdateForm.jsp";
	}

}
