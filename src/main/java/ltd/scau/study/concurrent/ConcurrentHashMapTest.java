package ltd.scau.study.concurrent;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Weijie Wu
 */
public class ConcurrentHashMapTest {

    static void count() {
        final int size = 233;
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            map.put(i, r.nextInt());
        }

        map.put(233333333, 23333);

        System.out.println(map.size());
    }

    public static void main(String[] args) {
        count();
    }
}
