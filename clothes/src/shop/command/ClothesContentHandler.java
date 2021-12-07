package shop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class ClothesContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
				List<QnaVO> qnaLists;
				String clothes_kind = request.getParameter("clothes_kind");
				int clothes_id = Integer.parseInt(request.getParameter("clothes_id"));
				
				//clothes_id에 해당하는 상품을 얻어냄
				ManagerDAO clothesProcess = ManagerDAO.getInstance();
				ManagerVO clothes = clothesProcess.getClothes(clothes_id);
				
				//clothes_id에 해당하는 상품의 QnA 수를 얻어냄
				QnaDAO qnaProcess = QnaDAO.getInstance();
				int count = qnaProcess.getArticleCount(clothes_id);
						
				if (count > 0){//QnA가 있으면 수행
					///clothes_id에 해당하는 상품의 QnA를 얻어냄 
					qnaLists = qnaProcess.getArticles(count, clothes_id);
		        	request.setAttribute("qnaLists", qnaLists);
		        }

				request.setAttribute("clothes", clothes);
				request.setAttribute("clothes_id", new Integer(clothes_id));
				request.setAttribute("clothes_kind", clothes_kind);
				request.setAttribute("count", new Integer(count));
				request.setAttribute("type", new Integer(1));
				return "/shop/clothesContent.jsp";
	}

}
