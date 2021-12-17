package buy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buy.dao.BuyDAO;
import cart.dao.CartDAO;
import cart.dto.CartVO;
import common.command.CommandHandler;
import member.dao.LogonDAO;
import member.dto.LogonVO;

public class BuyFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");

		List<CartVO> cartLists = null;
		LogonVO member= null;
		int count = 0;
		
		//해당 buyer의 장바구니 목록의 수를 얻어냄
		CartDAO clothesProcess = CartDAO.getInstance();
		count = clothesProcess.getListCount(buyer);
				
		if(count > 0){//장바구니 목록이 있으면 수행
			//구매에 필요한 해당 buyer의 장바구니 목록을 얻어냄
			cartLists = clothesProcess.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
		//구매에 필요한 buyer의 정보를 얻어냄
		LogonDAO memberProcess = LogonDAO.getInstance();
		member = memberProcess.getMember(buyer);
			
		request.setAttribute("member", member);
		request.setAttribute("type", new Integer(1));
		return "/buy/buyForm.jsp";
	}
}
