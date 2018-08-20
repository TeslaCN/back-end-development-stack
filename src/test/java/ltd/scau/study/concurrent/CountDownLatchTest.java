package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Weijie Wu
 */
class CountDownLatchTest {

    @Test
    void test() throws InterruptedException {
        final int toBeDone = 10;
        Random r = new Random();
        CountDownLatch latch = new CountDownLatch(toBeDone);
        System.out.println("TDB: " + toBeDone);
        AtomicInteger count = new AtomicInteger();
        Runnable killer = () -> {
            final int num = count.incrementAndGet();
            try {
                TimeUnit.MILLISECONDS.sleep(1000 + r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("Killed: " + num);
        };

        for (int i = 0; i < toBeDone; i++) {
            new Thread(killer).start();
        }

        latch.await();
        System.out.println("Mission Completed! " + toBeDone);
    }

}