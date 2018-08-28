package ltd.scau.study.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Weijie Wu
 */
public class WaitAndNotify {

    public static void main(String[] args) throws Exception {
        waitAndNotify();
        park();
        parkObject();
        waitAndNotifyOther();
    }

    static void parkObject() throws InterruptedException {
        AtomicReference<Boolean> condition = new AtomicReference<>(false);
        Runnable consumer = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Park. " + Thread.currentThread().getName());
                while (!condition.get()) {
                    LockSupport.park(condition);
                }
                System.out.println("Got. " + Thread.currentThread().getName());
                condition.set(false);
            }
        };
        Thread c = new Thread(consumer);

        Runnable producer = () -> {
            for (; ; ) {
                try {
                    Thread.sleep((long) (100 + Math.random() * 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Unpark");
                condition.set(true);
                System.out.println(LockSupport.getBlocker(c));
                LockSupport.unpark(c);
            }
        };
        Thread p = new Thread(producer);
        p.setDaemon(true);
        p.start();
        c.start();
        c.join();
    }

    static void waitAndNotifyOther() throws InterruptedException {
        AtomicReference<Boolean> condition = new AtomicReference<>(false);
        AtomicReference<Boolean> condition2 = new AtomicReference<>(false);
        Runnable consumer = () -> {
            for (int i = 0; i < 3; i++) {
                synchronized (condition2) {
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
                    Thread.sleep((long) (100 + Math.random() * 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (condition2) {
                    System.out.println("Nofity");
                    condition.set(true);
//                    condition.notify();
                    condition.notifyAll();
                }
            }
        };

        Thread p = new Thread(producer);
        p.setDaemon(true);
        p.start();
        Thread c = new Thread(consumer);
        c.start();
        c.join();
//        Thread thread = new Thread(consumer);
//        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
//        new Thread(consumer).start();
    }
    static void waitAndNotify() throws InterruptedException {
        AtomicReference<Boolean> condition = new AtomicReference<>(false);
        Runnable consumer = () -> {
            for (int i = 0; i < 3; i++) {
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
                    Thread.sleep((long) (100 + Math.random() * 200));
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

        Thread p = new Thread(producer);
        p.setDaemon(true);
        p.start();
        Thread c = new Thread(consumer);
        c.start();
        c.join();
//        Thread thread = new Thread(consumer);
//        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
//        new Thread(consumer).start();
    }

    static void park() throws InterruptedException {
        AtomicReference<Boolean> condition = new AtomicReference<>(false);
        Runnable consumer = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Park. " + Thread.currentThread().getName());
                while (!condition.get()) {
//                    condition.wait();
                    LockSupport.park();
                }
                System.out.println("Got. " + Thread.currentThread().getName());
                condition.set(false);
            }
        };
        Thread c = new Thread(consumer);

        Runnable producer = () -> {
            for (; ; ) {
                try {
                    Thread.sleep((long) (100 + Math.random() * 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Unpark");
                condition.set(true);
                LockSupport.unpark(c);
//                condition.notifyAll();
            }
        };
        Thread p = new Thread(producer);
        p.setDaemon(true);
        p.start();
        c.start();
        c.join();
//        Thread thread = new Thread(consumer);
//        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
//        new Thread(consumer).start();
    }
}
