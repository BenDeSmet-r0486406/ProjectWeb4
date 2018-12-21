package view.handlers;

import db.DbException;
import domain.Person;
import domain.PersonStatus;
import util.Hashing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Mapping("register")
public class SignUp extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String repeatPassword = request.getParameter("repeatPassword");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");

		Person p = new Person();
		List<String> errors = new ArrayList<String>();

		if(password.equals(repeatPassword)){
			this.setEmail(errors, p, email);
			this.setFirstName(errors, p, firstName);
			this.setLastName(errors, p, lastName);
			this.setPassword(errors, p, password);
			this.setAge(errors, p, age);
			this.setGender(errors, p, gender);
			p.setState(PersonStatus.ONLINE);
		} else {
			errors.add("Password fields don't match");
		}


		try{
			if(errors.isEmpty()){
				getPersonService().addPerson(p);
			}
		}catch(DbException e){
			errors.add(e.getMessage());
			e.printStackTrace();
		}

		if(errors.isEmpty()){
			response.sendRedirect("Controller?");
		}else{
			request.setAttribute("errors", errors);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
		}
	}

	private void setPassword(List<String> errors, Person p, String password) {
		try{
			p.setPassword(password);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void setLastName(List<String> errors, Person p, String lastName) {
		try{
			p.setLastName(lastName);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void setFirstName(List<String> errors, Person p, String firstName) {

		try{
			p.setFirstName(firstName);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void setEmail(List<String> errors, Person p, String email) {
		try{
			p.setUserId(Hashing.SHA256(email, "").substring(0,8));
			p.setEmail(email);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void setAge(List<String> errors, Person p, String age) {
		try{
			p.setAge(age);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void setGender(List<String> errors, Person p, String gender) {
		try{
			p.setGender(gender);
		}catch(IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}
}
