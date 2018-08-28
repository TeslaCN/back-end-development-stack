package ltd.scau.study.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Weijie Wu
 */
public class ReentrantLockTest {

    private static volatile int count;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        final int threadCount = 2;

        Runnable adder = () -> {
            try {
                lock.lock();
                count++;
            } finally {
                lock.unlock();
            }
        };

        for (int i = 0; i < threadCount; i++) {
            new Thread(adder).start();
        }
    }
}
