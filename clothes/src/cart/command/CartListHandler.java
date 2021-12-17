package cart.command;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;
import cart.dto.CartVO;
import common.command.CommandHandler;

public class CartListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		
		List<CartVO> cartLists = null;
		int count = 0;
		
		//해당 buyer의 장바구니의 목록 수 얻어냄
		CartDAO clothesProcess = CartDAO.getInstance();
		count = clothesProcess.getListCount(buyer);
		
		if(count > 0) { //해당 buyer의 장바구니 목록이있으면 수행
			//	해당 구매자의 목록을 얻어냄
			cartLists = clothesProcess.getCart(buyer, count);
			request.setAttribute("cartLists",cartLists);
		}
	
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(1));
		return "/cart/cartList.jsp";
	}
}