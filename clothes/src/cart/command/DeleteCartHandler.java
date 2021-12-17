package cart.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;
import common.command.CommandHandler;

public class DeleteCartHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String list = request.getParameter("list");
		String msg = "";
		
		CartDAO clothesProcess = CartDAO.getInstance();
		
		if(list.equals("all")) { //list값이 all일때 수행 
			//해당 바이어의 장바구니 비우기
			String buyer = request.getParameter("buyer");
			clothesProcess.deleteAll(buyer);
			msg = "장바구니를 전부 비웠습니다." ;
		}else { //list값이 all이외 (cart_id)의 값이면 수행
			// list값(cart_id)에 해당하는 레코드 삭제
			clothesProcess.delectList(Integer.parseInt(list));
			msg = "지정항목이 삭제되었습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("type", new Integer(1));
		return "/cart/deleteCart.jsp";
	}

}
