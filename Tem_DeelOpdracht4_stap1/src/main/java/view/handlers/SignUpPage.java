package view.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.handlers.Mapping;
import view.handlers.RequestHandler;
@Mapping("signUp")
public class SignUpPage extends RequestHandler{
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signUp.jsp").forward(request, response);
	}
}
