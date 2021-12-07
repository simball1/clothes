package shop.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import manager.dao.ManagerDAO;
import manager.dto.ManagerVO;

public class ShopMainHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		ManagerVO clothesList[] = null;
		List<ManagerVO[]> clothesLists = new ArrayList<ManagerVO[]>();
		
		ManagerDAO clothesProcess = ManagerDAO.getInstance();//DB연동
		
		//카테고리별 최신의 상품 3개씩 얻어내서 List에 저장
		for(int i=1; i<=3;i++){
			clothesList = clothesProcess.getClothess(i+"00",3);
			clothesLists.add(clothesList);
		}
		
        //해당 페이지로 보낼 내용 설정
        request.setAttribute("clothesLists", clothesLists);
        //사용자 화면을 의미하는 값을 설정
		request.setAttribute("type", new Integer(1));
		return "/shop/shopMain.jsp";
	}

}
