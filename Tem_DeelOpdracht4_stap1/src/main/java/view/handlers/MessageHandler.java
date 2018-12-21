package view.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("message")
public class MessageHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = getPersonIfAutheticated(request);
        String other_id = request.getParameter("userid");
        String message = request.getParameter("message");
        Person other = getPersonService().getPerson(other_id);
        getPersonService().sendMessage(user, other, message);
    }
}
