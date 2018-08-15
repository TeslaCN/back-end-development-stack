package ltd.scau.study.basic.practice;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author Weijie Wu
 */
public class SerializableTest {

    @Test
    public void readWriteObject() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Person p = new Person("23333", "Nikola Tesla", 99);
        oos.writeObject(p);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Person p1 = (Person) ois.readObject();
        System.out.println(p1);
    }

}
