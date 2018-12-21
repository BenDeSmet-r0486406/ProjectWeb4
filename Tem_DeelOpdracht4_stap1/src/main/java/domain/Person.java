package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import util.Hashing;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userId;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt;
	private String email;
	private String firstName;
	private String lastName;
	private PersonStatus state;
	@JsonIgnore
	private String message;
	private String gender;
	private String age;

	public Person(String email, String password, String firstName, String lastName, String gender) {
		setUserId(Hashing.SHA256(email, "").substring(0,8));
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setGender(gender);
		setState(PersonStatus.ONLINE);
	}

	public Person(String email, String password, String firstName, String lastName) {
		setUserId(Hashing.SHA256(email, "").substring(0,8));
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setState(PersonStatus.ONLINE);
	}

	public Person(String userId, String email, String password, String salt, String firstName, String lastName) {
		setUserId(userId);
		setHashedPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setState(PersonStatus.ONLINE);
	}

	public Person() {
	}

	public void setEmail(String email) {
		if (email.equals("")) {
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.equals("")) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(Hashing.SHA256(password, getSalt()));
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if (gender.equals("")) {
			throw new IllegalArgumentException("No gender given");
		}
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		if (age.equals("")) {
			throw new IllegalArgumentException("No age given");
		}
		this.age = age;
	}

	public void setPassword(String password) {
		if (password.equals("")) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	public void setHashedPassword(String password) {
		if (password.equals("")) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	private String hashPassword(String password) {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);

		String salt = new BigInteger(1, seed).toString(16);
		this.setSalt(salt);

		return Hashing.SHA256(password, salt);
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.equals("")) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;// firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.equals("")) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setState(PersonStatus state){
		this.state = state;
	}

	public PersonStatus getState() {
		return state;
	}

	public void setMessage(String msg){
		this.message = msg;
	}

	public String getMessage(){
		return this.message;
	}

	public String getStatusMessage(){
		switch (getState()){
			case CUSTOM:
				return getMessage();
			default:
				return getState().toString();
		}
	}

	public String getFullName(){
		return String.format("%s %s", getFirstName(), getLastName());
	}

	@Override
	public String toString(){
		return getUserId();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Person){
			return equals((Person) o);
		}
		return false;
	}

	@Override
	public int hashCode(){
		return getUserId() != null ? this.getUserId().hashCode() : 0;
	}

	public boolean equals(Person p){
		return getUserId() != null && getUserId().equals(p.getUserId());
	}

	public String getEmail() {
		return email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
