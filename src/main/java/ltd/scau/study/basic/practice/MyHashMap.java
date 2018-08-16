package ltd.scau.study.basic.practice;

import java.util.*;

/**
 * @author Weijie Wu
 * HashMap简单实现
 */
public class MyHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private MyEntry<K, V>[] table;

    private int size = 0;

    private int capacity;

    public MyHashMap() {
        table = new MyEntry[DEFAULT_CAPACITY];
        capacity = table.length;
    }

    private int indexFor(Object key) {
        return key.hashCode() & capacity - 1;
    }

    private static class MyEntry<K, V> implements Entry<K, V> {

        private final int hashCode;

        private final K key;

        private V value;

        private MyEntry<K, V> next = null;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
            hashCode = key.hashCode();
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
            return Objects.equals(key, myEntry.key);
        }

        @Override
        public int hashCode() {

            return hashCode;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        MyEntry<K, V> e = table[indexFor(key)];
        while (e != null) {
            if (e.key.equals(key)) {
                return true;
            }
            e = e.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            MyEntry<K, V> e = table[i];
            while (e != null) {
                if (value.equals(e.value)) {
                    return true;
                }
                e = e.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        MyEntry<K, V> e = table[indexFor(key)];
        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = indexFor(key);
        MyEntry<K, V> e = table[index];
        if (e == null) {
            table[index] = new MyEntry<>(key, value);
        } else {
            MyEntry<K, V> pre = e;
            while (e != null) {
                if (e.key.equals(key)) {
                    return e.setValue(value);
                }
                pre = e;
                e = e.next;
            }
            pre.next = new MyEntry<>(key, value);
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int i = indexFor(key);
        MyEntry<K, V> e = table[i];
        if (e == null) {
            return null;
        }
        if (e.key.equals(key)) {
            table[i] = e.next;
            size--;
            return e.value;
        }
        MyEntry<K, V> pre = e;
        while ((e = e.next) != null) {
            if (e.key.equals(key)) {
                pre.next = e.next;
                size--;
                return e.value;
            } else {
                pre = e;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K k : m.keySet()) {
            put(k, m.get(k));
        }
    }

    @Override
    public void clear() {
        table = new MyEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            MyEntry<K, V> e = table[i];
            while (e != null) {
                set.add(e.getKey());
                e = e.next;
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            MyEntry<K, V> e = table[i];
            while (e != null) {
                collection.add(e.getValue());
                e = e.next;
            }
        }
        return collection;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            MyEntry<K, V> e = table[i];
            while (e != null) {
                set.add(e);
                e = e.next;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, i * 2);
        }
        for (Integer i : map.keySet()) {
            System.out.println(map.remove(i));
        }
    }
}
