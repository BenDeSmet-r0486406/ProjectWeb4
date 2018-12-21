package view.handlers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Mapping("searchUser")
public class UserFinder extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("key");
		Set<?> searchresult = getPersonService().search(search);
		searchresult.remove(getPersonIfAutheticated(request));
		searchresult.removeAll(getPersonService().getFriends(getPersonIfAutheticated(request)));
		RequestHandler.sendObjectAsJson(response, searchresult);

	}
}
