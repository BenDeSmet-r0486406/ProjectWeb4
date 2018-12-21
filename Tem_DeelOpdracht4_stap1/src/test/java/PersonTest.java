import domain.Person;
import org.junit.Test;
import util.Hashing;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testEquals(){
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens");
        Person an = new Person("an@ucll.be", "t", "An", "Cornelissen");
        Person jant = new Person("jan@ucll.be", "t", "Jan", "Man");
        assertNotEquals(jan, an);
        assertEquals(jan, jant);
    }

    @Test
    public void testHashCode(){
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens");
        Person jant = new Person("jan@ucll.be", "t", "Jan", "Man");
        assertEquals(jan.hashCode(), jant.hashCode());
    }

    @Test
    public void testHashedPassword(){
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens");
        Person an = new Person(Hashing.SHA256("an@ucll.be", ""), "an@ucll.be", Hashing.SHA256("t",""), "", "An", "Cornelissen");
        assertTrue(jan.isCorrectPassword("t"));
        assertTrue(!jan.isCorrectPassword("ta"));
        assertEquals(an.getPassword(), Hashing.SHA256("t",""));
    }

}
