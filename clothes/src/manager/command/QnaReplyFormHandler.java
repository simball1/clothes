package manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaReplyFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//qna_id에 해당하는 QnA를 가져옴
		QnaDAO qnaProcess = QnaDAO.getInstance();
		QnaVO qna  =  qnaProcess.updateGetArticle(qna_id);
		
		//QnA답변에 필요한 정보를 얻어냄
		int clothes_id = qna.getClothes_id();
		String clothes_title = qna.getClothes_title();
		String qna_content = qna.getQna_content();
		byte qora = 2;//답변글
		
		request.setAttribute("qna_id", new Integer(qna_id));
		request.setAttribute("clothes_id", new Integer(clothes_id));
		request.setAttribute("clothes_title", clothes_title);
		request.setAttribute("qna_content", qna_content);
		request.setAttribute("qora", new Integer(qora));
		request.setAttribute("type", new Integer(0));
		return "/manager/qnaProcess/qnaReplyForm.jsp";
	}

}
