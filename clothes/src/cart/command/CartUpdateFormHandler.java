package cart.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;

public class CartUpdateFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String cart_id = request.getParameter("cart_id");
		String buy_count = request.getParameter("buy_count");
		
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("buy_count", buy_count);
		request.setAttribute("type", new Integer(1));
		return "/cart/cartUpdateForm.jsp";
	}

}
