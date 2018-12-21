package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.PostRepository;
import db.PostRepositoryStub;
import domain.Person;
import domain.PersonService;
import domain.PersonStatus;
import view.handlers.RequestHandler;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PersonService model;
	private RequestHandlerFactory requestHandlerFactory;
	private PostRepository posts;

	@Override
	public void init() {
		try {
			super.init();
			model = new PersonService();
			this.requestHandlerFactory = new RequestHandlerFactory(model);
			this.posts = PostRepositoryStub.getInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try{
			String action = request.getParameter("action");
			request.setAttribute("statuslist", PersonStatus.values());

            Person p = (Person) request.getSession().getAttribute("user");
            request.setAttribute("user", p);
            request.setAttribute("posts", posts.getPosts());

			if (action != null) {
				RequestHandler handler;
				try {
					handler = requestHandlerFactory.create(action);
					handler.handleRequest(request, response);
				}
				catch (NotAuthorizedException exc) {
					List<String> errors = new ArrayList<>();
					errors.add(exc.getMessage());
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("index.jsp").forward(request,response);
				}
			}else{
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200/*");
		resp.setHeader("Access-Control-Allow-Methods", "GET");
	}

}
