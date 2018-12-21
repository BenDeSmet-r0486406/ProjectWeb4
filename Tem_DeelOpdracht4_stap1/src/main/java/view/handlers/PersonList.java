package view.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("personList")
public class PersonList extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.sendObjectAsJson(response, getPersonService().getPersons());
    }
}
