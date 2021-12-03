package common.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);	// 없는 URL (404 Not Found)
		return null;
	}
}