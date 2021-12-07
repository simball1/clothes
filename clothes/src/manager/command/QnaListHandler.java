package manager.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<QnaVO> qnaLists;
		
		//DB연동 - 상품QnA의 수를 얻어낸
		QnaDAO qnaProcess = QnaDAO.getInstance();
		int count = qnaProcess.getArticleCount();
		
		if (count > 0){//상품QnA가 있으면 수행
			//지정한 수만큼의 상품QnA를 얻어냄
			qnaLists = qnaProcess.getArticles(count);
        	request.setAttribute("qnaLists", qnaLists);
        }
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(0));
		return "/manager/qnaProcess/qnaList.jsp";
	}
}
