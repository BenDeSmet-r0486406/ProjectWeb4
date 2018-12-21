package db;

import domain.Message;
import domain.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MessageRepositoryStub implements MessageRepository {

    private List<Message> messages;

    public MessageRepositoryStub(){
        messages = new ArrayList<>();
    }

    @Override
    public void message(Person a, Person b, String message) {
        messages.add(new Message(message, new Date(),a,b));
    }

    @Override
    public List<Message> getMessagesBetween(Person a, Person b) {
        return messages.stream().filter(m -> m.toAndFromOrFromAndTo(a,b)).collect(Collectors.toList());
    }

    @Override
    public List<Message> getMessagesBetweenSince(Person a, Person b, Date dt) {
        return messages.stream().filter(m -> m.toAndFromOrFromAndTo(a,b) && m.getWhen().after(dt)).collect(Collectors.toList());
    }
}
