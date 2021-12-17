package cart.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;
import cart.dto.CartVO;
import common.command.CommandHandler;

public class InsertCartHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//장바구니에 추가할 정보를 파라미터에서 받아냄
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		int clothes_id = Integer.parseInt(request.getParameter("clothes_id"));
		String clothes_title = request.getParameter("clothes_title");
		String clothes_image = request.getParameter("clothes_image");
		int buy_price = (int)Float.parseFloat(request.getParameter("buy_price"));
		String clothes_size = request.getParameter("clothes_size");
		String buyer = request.getParameter("buyer");
				
		//장바구니에 추가하기 위한 정보구성
		CartVO cart = new CartVO();
		cart.setClothes_id(clothes_id);
		cart.setClothes_image(clothes_image);
		cart.setClothes_title(clothes_title);
		cart.setBuy_count(buy_count);
		cart.setBuy_price(buy_price);
		cart.setClothes_size(clothes_size);
		cart.setBuyer(buyer);
				
		//장바구니 추가
		CartDAO clothesProcess = CartDAO.getInstance();
		clothesProcess.insertCart(cart);
		return "/cart/insertCart.jsp";
	}
}
