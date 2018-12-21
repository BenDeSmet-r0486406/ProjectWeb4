package view.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("addUser")
public class UserAdder extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		Person p = (Person) request.getSession().getAttribute("user");
		Person f = getPersonService().getPerson(key);
		if(f instanceof Person){
			getPersonService().addFriends(p, f);
		}
		response.setContentType("application/json");
	}
}
