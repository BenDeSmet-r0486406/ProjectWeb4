package view.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.Person;
import domain.PersonService;
import util.Jsoniser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

	private PersonService personService;

	public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void setModel (PersonService personService) {
		this.personService = personService;
	}

	public PersonService getPersonService() {
		return personService;
	}


	protected Person getPersonIfAutheticated(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null ? (Person)request.getSession().getAttribute("user"): null;
	}

	public static void sendObjectAsJson(HttpServletResponse response, Object o){
		try {
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			sendJSON(response, Jsoniser.toJSON(o));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public static void sendJSON(HttpServletResponse response, String json){
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
