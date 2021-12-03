package manager.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;

public class ClothesRegisterFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		// TODO Auto-generated method stub
		
		request.setAttribute("type", new Integer(0));
		return "/manager/productProcess/clothesRegisterForm.jsp";
	}

	
}
