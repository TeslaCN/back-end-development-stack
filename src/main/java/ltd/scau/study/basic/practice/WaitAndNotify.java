package ltd.scau.study.basic.practice;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Weijie Wu
 */
public class WaitAndNotify {

    public static void main(String[] args) {

        AtomicReference<Boolean> condition = new AtomicReference<>(false);
        Runnable consumer = () -> {
            for (; ; ) {
                synchronized (condition) {
                    System.out.println("Wait. " + Thread.currentThread().getName());
                    while (!condition.get()) {
                        try {
                            condition.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Got. " + Thread.currentThread().getName());
                    condition.set(false);
                }
            }
        };
        Runnable producer = () -> {
            for (; ; ) {
                try {
                    Thread.sleep((long) (1000 + Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (condition) {
                    System.out.println("Nofity");
                    condition.set(true);
//                    condition.notify();
                    condition.notifyAll();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
        Thread thread = new Thread(consumer);
        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
//        new Thread(consumer).start();
    }
}
