package ltd.scau.study.basic.practice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author Weijie Wu
 */
public class User extends Person implements Externalizable {

    private String password;

    public User() {
        System.out.println("Constructor");
    }

    public User(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(password);
        System.out.println("Write External");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        password = in.readUTF();
        System.out.println("Read External");
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                '}';
    }
}
