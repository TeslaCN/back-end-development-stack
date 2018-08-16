package ltd.scau.study.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Weijie Wu
 */
public class HashMapConcurrent {

    public static void main(String[] args) {
        /**
         * JDK1.8好像不起作用
         */
        Map<Integer, Integer> map = new HashMap<>();

        final int size = Byte.MAX_VALUE;
        final int max = Byte.MAX_VALUE;
        final int threadCount = 128;

        Runnable producer = () -> {
            System.out.println("Producer Start: " + Thread.currentThread().getName());
            Random r = new Random();
            for (int i = 0; i < size; i++) {
                map.put(r.nextInt(max), i);
            }
            System.out.println("Producer Done: " + Thread.currentThread().getName());
        };

        Runnable consumer = () -> {
            System.out.println("Consumer Start: " + Thread.currentThread().getName());
            Random r = new Random();
            for (int i = 0; i < size; i++) {
                map.get(r.nextInt(max));
            }
            System.out.println("Consumer Done: " + Thread.currentThread().getName());
        };

        for (int i = 0; i < threadCount; i++) {
            new Thread(producer).start();
            new Thread(consumer).start();
        }
    }
}
