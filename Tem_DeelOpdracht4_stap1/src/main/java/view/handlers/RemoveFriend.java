package view.handlers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("removefriend")
public class RemoveFriend extends RequestHandler{
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("key");
		getPersonService().removeFriend(getPersonIfAutheticated(request), userid);
		response.setContentType("application/json");
	}
}
