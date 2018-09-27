package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

/**
 * @author Weijie Wu
 */
class ThreadTest {

    @Test
    void multiStart() {
        Thread t = new Thread(() -> {
            System.out.println("Thread Started!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ends!");
        });

        t.start();
        t.start();
    }


    @Test
    void startAgain() throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("Thread Started!");
            System.out.println("Thread ends!");
        });

        t.start();
        Thread.sleep(1000);
        t.start();
    }
}
