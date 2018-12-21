package view.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Mapping("messages")
public class MessageListHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = getPersonIfAutheticated(request);
		String when = request.getParameter("when");
		try{
			long dt = Long.parseLong(when);
			sendObjectAsJson(response, getPersonService().getMessages(user, new Date(dt)));
		}catch (NumberFormatException e){
			System.out.println("Error parsing: "+ when);
			sendObjectAsJson(response, getPersonService().getMessages(user, new Date()));
		}
	}
}
