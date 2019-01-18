import org.junit.Test;

import entity.Person;

/**
 * Created by heron.lee 2019-01-18
 */
public class PersonTest {

    @Test
    public void personTest() {
        Person person = new Person();
        person.setAge(10);
        person.setName("eee");
        System.out.println("name: " + person.getName() + ", age: " + person.getAge());
    }
}
