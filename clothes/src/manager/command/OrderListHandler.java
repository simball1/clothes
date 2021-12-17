package manager.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buy.dao.BuyDAO;
import buy.dto.BuyVO;
import common.command.CommandHandler;

public class OrderListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<BuyVO> buyLists = null;
		int count = 0;

		//전체 주문목록의 수를 얻어냄
		BuyDAO buyProcess = BuyDAO.getInstance();
		count = buyProcess.getListCount();
		
		if(count > 0){//주문목록이 있으면
			//전체 주문목록을 얻어냄
			buyLists = buyProcess.getBuyList();
		    request.setAttribute("buyLists", buyLists);
		}
		
		request.setAttribute("count", new Integer(count));
	    request.setAttribute("type", new Integer(0));
		return "/manager/orderedProduct/orderList.jsp";
	}
}
