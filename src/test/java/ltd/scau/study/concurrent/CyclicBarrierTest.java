package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Weijie Wu
 */
class CyclicBarrierTest {

    @Test
    void test() throws BrokenBarrierException, InterruptedException {
        final int max = 10;
        CyclicBarrier barrier = new CyclicBarrier(max, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("All person ready.");
        });
        AtomicInteger ai = new AtomicInteger();
        Runnable ready = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Person " + ai.incrementAndGet() + " arrived.");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < max - 1; i++) {
            new Thread(ready).start();
        }
        barrier.await();
        System.out.println("Main");

    }
}
