package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MutexTest {

    private Mutex lock = new Mutex();

    private volatile int count = 0;

    private static final int THREAD_NUM = 16;

    private static final int LOOP = 1000;

    private CountDownLatch latch = new CountDownLatch(THREAD_NUM);

    private Runnable a = () -> {
        for (int i = 0; i < LOOP; i++) {
            try {
                lock.lock();
                count++;
            } finally {
                lock.unlock();
            }
        }
        latch.countDown();
    };

    @Test
    void lock() throws InterruptedException {
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(a).start();
        }
        latch.await();
        System.out.println(count);
        Assertions.assertEquals(THREAD_NUM * LOOP, count);
    }

    @Test
    void tryLock() {
    }

    @Test
    void unlock() {
    }

    @Test
    void newCondition() {
    }

    @Test
    void isLocked() {
    }

    @Test
    void hasQueuedThreads() {
    }

    @Test
    void lockInterruptibly() {
    }

    @Test
    void tryLock1() {
    }
}