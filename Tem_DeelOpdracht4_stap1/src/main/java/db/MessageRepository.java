package db;

import domain.Message;
import domain.Person;

import java.util.Date;
import java.util.List;

public interface MessageRepository {

    void message(Person a, Person b, String message);

    List<Message> getMessagesBetween(Person a, Person b);
    List<Message> getMessagesBetweenSince(Person a, Person b, Date dt);
}
