package ltd.scau.study.basic.practice;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
class UserTest {

    @Test
    void serializeTest() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(baos);
        User u = new User("sudo reboot");
        os.writeObject(u);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream is = new ObjectInputStream(bais);
        User o = (User) is.readObject();
        System.out.println(o);
    }

}