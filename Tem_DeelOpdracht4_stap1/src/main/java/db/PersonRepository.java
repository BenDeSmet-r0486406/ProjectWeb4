package db;

import java.util.List;
import java.util.Set;

import domain.Person;

public interface PersonRepository {

	public abstract void add(Person person);

	public abstract void delete(String userId);

	public abstract Person get(String userId);

	public abstract List<Person> getAll();
	
	public abstract Person getAuthenticatedUser(String email, String password);

	public abstract void update(Person person);
	public abstract Set<Person> search(String search);

	public abstract Set<Person> getFriends(Person person);
	public abstract void addFriends(Person p1, Person p2);

	public abstract void removeFriend(Person personIfAutheticated, String userid);
}
