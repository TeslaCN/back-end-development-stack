package ltd.scau.study.basic.practice;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MyHashMapTest {

    private Map<Integer, Integer> myHashMap = new MyHashMap<>();
    private Map<Integer, Integer> hashMap = new HashMap<>();

    private Random random = new Random();

    @BeforeAll
    public void init(TestReporter reporter) {
        for (int i = 0; i < 10; i++) {
            int k = random.nextInt();
            int v = random.nextInt();
            myHashMap.put(k, v);
            hashMap.put(k, v);
            reporter.publishEntry(String.valueOf(k), String.valueOf(v));
        }
    }

    @Test
    void size() {
        assertEquals(hashMap.size(), myHashMap.size());
    }

    @Test
    void isEmpty() {
        assertEquals(hashMap.isEmpty(), myHashMap.isEmpty());
    }

    @Test
    void containsKey() {
        for (Integer i : hashMap.keySet()) {
            assertEquals(hashMap.containsKey(i), myHashMap.containsKey(i));
        }
    }

    @Test
    void containsValue() {
        for (Integer i : hashMap.values()) {
            assertEquals(hashMap.containsValue(i), myHashMap.containsValue(i));
        }
    }

    @Test
    void get() {
        for (Integer i : hashMap.keySet()) {
            assertEquals(hashMap.get(i), myHashMap.get(i));
        }
    }

    @Test
    void put() {
        for (Integer i : hashMap.keySet()) {
            assertEquals(myHashMap.put(i, myHashMap.get(i) + 1), hashMap.put(i, hashMap.get(i) + 1));
        }
    }

    @AfterAll
    void remove() {
        Integer[] keySet = hashMap.keySet().toArray(new Integer[0]);
        for (Integer i : keySet) {
            assertEquals(hashMap.remove(i), myHashMap.remove(i));
        }
    }

    @Test
    void putAll() {
    }

//    @AfterAll
//    void clear() {
//        hashMap.clear();
//        myHashMap.clear();
//        assertEquals(hashMap.isEmpty(), myHashMap.isEmpty());
//        assertEquals(hashMap.size(), myHashMap.size());
//    }

    @Test
    void keySet(TestReporter reporter) {
        Integer[] keys = hashMap.keySet().toArray(new Integer[0]);
        Integer[] myKeys = myHashMap.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        Arrays.sort(myKeys);
        reporter.publishEntry("  HashMap Keys", Arrays.toString(keys));
        reporter.publishEntry("MyHashMap Keys", Arrays.toString(myKeys));
        assertArrayEquals(keys, myKeys);
    }

    @Test
    void values(TestReporter reporter) {
        Integer[] values = hashMap.values().toArray(new Integer[0]);
        Integer[] myValues = myHashMap.values().toArray(new Integer[0]);
        Arrays.sort(values);
        Arrays.sort(myValues);
        reporter.publishEntry("  HashMap Values", Arrays.toString(values));
        reporter.publishEntry("MyHashMap Values", Arrays.toString(myValues));
        assertArrayEquals(values, myValues);
    }

    @Test
    void entrySet(TestReporter reporter) {
        reporter.publishEntry("target", "jdk");
        for (Map.Entry<Integer, Integer> e : hashMap.entrySet()) {
            reporter.publishEntry(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
        }
        reporter.publishEntry("target", "my");
        for (Map.Entry<Integer, Integer> e : myHashMap.entrySet()) {
            reporter.publishEntry(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
        }
    }
}