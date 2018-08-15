package ltd.scau.study.basic.practice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
class UsualRuntimeExceptionTest {

    @Test
    public void divideZero() {
        int i = 1 / 0;
    }

    @Test
    public void arrayType() {
        Object[] strings = new String[10];
        strings[0] = 64;
    }

    @Test
    public void arrayIndexOutOfBounds() {
        String[] strings = new String[10];
        String string = strings[10];
    }

    @Test
    public void classCast() {
        System.out.println((String) new Object());
    }

    @Test
    public void concurrentModification() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        for (Integer integer : l) {
            l.remove(integer);
        }
    }
}