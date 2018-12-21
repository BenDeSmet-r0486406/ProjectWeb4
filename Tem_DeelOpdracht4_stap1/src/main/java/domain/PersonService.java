package domain;

import java.util.*;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import db.MessageRepository;
import db.MessageRepositoryStub;
import db.PersonRepository;
import db.PersonRepositoryStub;

public class PersonService {
	private PersonRepository personRepository = new PersonRepositoryStub();
	private MessageRepository messageRepository = new MessageRepositoryStub();

	public PersonService(){
	}

	public Person getPerson(String personId)  {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}

	public Person getAuthenticatedUser(String email, String password) {
		return getPersonRepository().getAuthenticatedUser(email, password);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}

	public Set<Person> search(String search){
		return personRepository.search(search);
	}

	public void addFriends(Person p, Person f) {
		personRepository.addFriends(p, f);
	}

	public Set<Person> getFriends(Person p) {
		return personRepository.getFriends(p);
	}

	public void removeFriend(Person personIfAutheticated, String userid) {
		personRepository.removeFriend(personIfAutheticated, userid);
	}

    public void sendMessage(Person user, Person other, String message) {
		if(user != null && other != null && message != null && message.length() > 0){
			messageRepository.message(user, other, message);
		}
    }

	public Map<Person, List<Message>> getMessages(Person user, Date since) {
		Map<Person, List<Message>> result = new HashMap<>();
		getFriends(user).forEach(p -> result.put(p, messageRepository.getMessagesBetweenSince(user, p, since)));
		return result;
	}
}
