package ltd.scau.study.collection;

import org.junit.jupiter.api.RepeatedTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
class HashMapConcurrentTest {

    @RepeatedTest(1000)
    public void hashMap() {
        Map<Integer, Integer> map = new HashMap<>(2);

//        final int size = Integer.MAX_VALUE;
        final int size = Short.MAX_VALUE;

        final int max = Byte.MAX_VALUE * 4;

        final int threadCount = 32;

        Runnable producer = () -> {
            System.out.println("Producer Start: " + Thread.currentThread().getName());
            Random r = new Random();
            for (int i = 0; i < size; i++) {
                map.put(new Integer(r.nextInt(max)), i);
            }
            System.out.println("Producer Done: " + Thread.currentThread().getName());
        };

        Runnable consumer = () -> {
            System.out.println("Consumer Start: " + Thread.currentThread().getName());
            Random r = new Random();
            for (int i = 0; i < size; i++) {
                map.get(new Integer(r.nextInt(max)));
            }
            System.out.println("Consumer Done: " + Thread.currentThread().getName());
        };

        for (int i = 0; i < threadCount; i++) {
            new Thread(producer).start();
            new Thread(producer).start();
            new Thread(consumer).start();
        }
    }


}
