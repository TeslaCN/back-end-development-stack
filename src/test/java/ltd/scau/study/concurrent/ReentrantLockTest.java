package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Weijie Wu
 */
class ReentrantLockTest {

    @Test
    void exceeded() {
        ReentrantLock lock = new ReentrantLock();
        System.out.println(lock.getHoldCount());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            lock.lock();
            lock.lock();
            int hold = lock.getHoldCount();
            if (hold % (Integer.MAX_VALUE / 10) < 2) {
                System.out.println(hold);
            }
        }
    }
}
