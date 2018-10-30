package ltd.scau.concurrent;

import java.util.ArrayDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Weijie Wu
 */
public class NofityAll {

    private static final int COUNT = 7;

    private static final CyclicBarrier BARRIER = new CyclicBarrier(COUNT + 1);

    private static final ArrayDeque<Integer> deque = new ArrayDeque<>();

    private static final Runnable a = () -> {
        System.out.println("Producer arrived");
        try {
            BARRIER.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (deque) {
            for (int i = 0; i < 8; i++) {
                deque.push(i);
            }
            System.out.println("Produced");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        synchronized (deque) {
            System.out.println("Notify a consumer");
            deque.notify();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (deque) {
            System.out.println("Notify another consumer");
            deque.notify();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (deque) {
            System.out.println("Notify All");
            deque.notifyAll();
        }
    };

    private static final Runnable consumer = () -> {
        try {
            System.out.println(Thread.currentThread().getName() + " arrived");
            BARRIER.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        synchronized (deque) {
            while (deque.isEmpty()) {
                try {
                    deque.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " >> " + deque.pop());
        }
    };


    public static void main(String[] args) {
        Thread p = new Thread(a);
        p.start();
        Thread[] consumers = new Thread[COUNT];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(consumer);
            consumers[i].start();
        }
    }
}
