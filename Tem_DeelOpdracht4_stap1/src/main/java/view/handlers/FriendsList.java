package view.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("friendsList")
public class FriendsList extends RequestHandler{
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person p = (Person) request.getSession().getAttribute("user");
		if(p != null){
			RequestHandler.sendObjectAsJson(response, getPersonService().getFriends(p));
		}
		return;
	}
}
