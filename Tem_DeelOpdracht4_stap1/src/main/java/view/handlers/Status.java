package view.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import domain.PersonStatus;
import view.handlers.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("status")
public class Status extends RequestHandler {

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		try{
			Person p = (Person) request.getSession().getAttribute("user");
			String status = request.getParameter("status");
			String message = request.getParameter("message");
			if(status != null && status != "" && p != null){
				p.setState(PersonStatus.valueOf(status));
				if(message != null && message != ""){
					p.setMessage(message);
				}
			}
			RequestHandler.sendObjectAsJson(response, p);
		} catch (ClassCastException e){
			e.printStackTrace();
		}
	}
}
