package db;

import java.util.*;
import java.util.stream.Collectors;

import domain.Person;
import util.Hashing;
import util.Tuple;

public class PersonRepositoryStub implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();
	private Set<Tuple<Person>> friends = new HashSet<>();

	public PersonRepositoryStub () {
		Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris", "Female");
		administrator.setAge("25");
		add(administrator);
		Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens",  "Male");
		jan.setAge("24");
		add(jan);
		Person an = new Person("an@ucll.be", "t", "An", "Cornelissen",  "X");
		an.setAge("23");
		add(an);
        Person ben = new Person("ben@ucll.be", "b", "Ben", "De Smet",  "Male");
        ben.setAge("23");
        add(ben);
        this.addFriends(jan, administrator);
        this.addFriends(jan, an);
        this.addFriends(ben, jan);
	}

	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}

	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(person.getUserId() == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserId())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserId(), person);
	}

	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getUserId(), person);
	}

	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}

	public Person getAuthenticatedUser(String email, String password) {
		Person person = get(Hashing.SHA256(email, "").substring(0,8));

		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}

	public Set<Person> search(final String search){
		return persons.values().stream().filter(person -> person.getFullName().contains(search)).collect(Collectors.toSet());
	}

	public Set<Person> getFriends(Person person){
		return friends.stream().filter(t -> t.contains(person)).map(t -> t.getOther(person)).collect(Collectors.toSet());
	}

	public void addFriends(Person p1, Person p2){
		if(p1 != null && !p1.equals(p2)){
			try {
				friends.add(new Tuple<>(p1,p2));
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void removeFriend(Person personIfAutheticated, String userid) {
		Person other = get(userid);
		if(personIfAutheticated != null && other != null){
			try {
				friends.remove(new Tuple<Person>(personIfAutheticated, other));
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
}
