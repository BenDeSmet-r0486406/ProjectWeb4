import db.MessageRepository;
import db.MessageRepositoryStub;
import domain.Person;
import org.junit.Assert;
import org.junit.Test;

public class MessageRepositoryStubTest {

	@Test
	public void testMessage(){
		MessageRepository repository = new MessageRepositoryStub();
		Person a = new Person("an@ucll.be","password","An","Alleman");
		Person b = new Person("jan@ucll.be","password","Jan","Alleman");
		Person c = new Person("ban@ucll.be","password","Ban","Alleman");
		repository.message(a, b, "Hallo");
		Assert.assertTrue(repository.getMessagesBetween(a, b).size() == 1);
		Assert.assertTrue(repository.getMessagesBetween(a, c).size() == 0);
	}

}
