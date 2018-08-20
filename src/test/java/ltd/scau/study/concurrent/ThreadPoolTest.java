package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Weijie Wu
 */
class ThreadPoolTest {

    @Test
    void executor() throws InterruptedException {
        final int coreSize = 8;
        final int maxSize = 16;

        final int taskCount = 32;

        AtomicInteger taskNum = new AtomicInteger();
        AtomicInteger threadNum = new AtomicInteger();
        ExecutorService executorService = new ThreadPoolExecutor(coreSize, maxSize, 1l, TimeUnit.NANOSECONDS,
//                new LinkedBlockingQueue<>(),
                new ArrayBlockingQueue<>(1),
                (r) -> {
                    Thread t = new Thread(r);
                    t.setName("Thread-" + threadNum.incrementAndGet());
                    return t;
                }, (r, executor) -> System.out.println("REJECT: " + r));

        Random r = new Random();
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " -> " + taskNum.incrementAndGet());
        };

        for (int i = 0; i < taskCount; i++) {
            executorService.execute(task);
        }
        TimeUnit.SECONDS.sleep(2);

        for (int i = 0; i < taskCount; i++) {
            if (i == taskCount >> 2) {
                executorService.shutdown();
            }
            executorService.execute(task);
        }

        executorService.shutdown();
        System.out.println("Shutdown and await");
        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }
}
