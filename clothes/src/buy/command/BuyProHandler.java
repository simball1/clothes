package buy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buy.dao.BuyDAO;
import cart.dao.CartDAO;
import cart.dto.CartVO;
import common.command.CommandHandler;

public class BuyProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		//구매 처리에 필요한 정보를 파라미터에서 얻어냄
		String deliveryName = request.getParameter("deliveryName");
		String deliveryTel = request.getParameter("deliveryTel");
		String deliveryAddess = request.getParameter("deliveryAddess");
		String buyer = request.getParameter("buyer");
		int count = 0;
				
		//구매처리를 위해 장바구니의 목록을 얻어냄
		CartDAO cartProcess = CartDAO.getInstance();
		count = cartProcess.getListCount(buyer);
		List<CartVO> cartLists = cartProcess.getCart(buyer,count);
				
		//장바구니의 목록, 구매자, 결제계좌, 배송지정보를
		//buy테이블에 추가
		BuyDAO buyProcess = BuyDAO.getInstance();
		buyProcess.insertBuy(cartLists,buyer,
		deliveryName, deliveryTel, deliveryAddess);

		request.setAttribute("orderStus", "주문완료");
		request.setAttribute("type", new Integer(1));
		return "/buy/buyPro.jsp";
	}
}