import domain.Person;
import org.junit.Test;
import util.Tuple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TupleTest {

    @Test
    public void testEquals() throws InstantiationException {
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens");
        Person an = new Person("an@ucll.be", "t", "An", "Cornelissen");
        Tuple<Person> ja = new Tuple<>(jan, an);
        Tuple<Person> aj = new Tuple<>(an, jan);
        Tuple<Person> aa = new Tuple<>(an, an);
        Tuple<Person> jj = new Tuple<>(jan, jan);
        Tuple<Double> dd = new Tuple<>(5.0,5.0);
        assertTrue(ja.equals(aj));
        assertTrue(aj.equals(ja));
        assertTrue(aj.equals(aj));
        assertTrue(ja.equals(ja));
        assertFalse(ja.equals(aa));
        assertFalse(ja.equals(jj));
        assertFalse(ja.equals(dd));
    }

    @Test
    public void testHashCode() throws InstantiationException {
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens");
        Person an = new Person("an@ucll.be", "t", "An", "Cornelissen");
        Tuple<Person> ja = new Tuple<>(jan, an);
        Tuple<Person> aj = new Tuple<>(an, jan);
        assertTrue(ja.hashCode() == aj.hashCode());
    }

    @Test
    public void testLargeHashCode() throws InstantiationException {
        //jan6a0ac0fd972c325d6ca5512b67a5e0ad996c4a3e9b59971d125164e6d4db1a1c8fb76e68cb6fc40d7b95a33cd4e2258c15f07d8ec3bfada83315d3297d5058560f3fe520ce048a404fe6350f56f7dbcc099e33d4a7616de9e5702c09c821bbf7cd444a3f8f8a29e501bbc3b5b8dad15130fdcba3983ae13cc854ea817730a7626e518f2f55dfacc79203deb1984dfa5d316daab92887bd23f00997acb80f95dbd2da2e0a32339676787eb08cedc678d3105beec51f0a88767b53ae923121746@ucll.be
        String id = "jan6a0ac0fd972c325d6ca5512b67a5e0ad996c4a3e9b59971d125164e6d4db1a1c8fb76e68cb6fc40d7b95a33cd4e2258c15f07d8ec3bfada83315d3297d5058560f3fe520ce048a404fe6350f56f7dbcc099e33d4a7616de9e5702c09c821bbf7cd444a3f8f8a29e501bbc3b5b8dad15130fdcba3983ae13cc854ea817730a7626e518f2f55dfacc79203deb1984dfa5d316daab92887bd23f00997acb80f95dbd2da2e0a32339676787eb08cedc678d3105beec51f0a88767b53ae923121746@ucll.be";
        Person jan = new Person(id, "t", "Jan", "Janssens");
        Tuple<Person> jj = new Tuple<>(jan, jan);
        jj.hashCode();
    }
}
