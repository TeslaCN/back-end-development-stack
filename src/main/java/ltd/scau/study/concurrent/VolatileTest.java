package ltd.scau.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Weijie Wu
 */
public class VolatileTest {

    private static boolean init = false;

    private static Object context;

    /**
     * 貌似不起作用
     * 
     */
    public static void runTest() {

        CyclicBarrier barrier = new CyclicBarrier(2);

        Runnable t1 = () -> {
//            try {
//                barrier.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
            context = loadContext();
            init = true;
        };

        Runnable t2 = () -> {
//            try {
//                barrier.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
            while (!init) {
                System.out.println("Not init");
            }
            doSomeThing(context);
        };

        new Thread(t1).start();
        new Thread(t2).start();

    }

    public static void main(String[] args) {

    }

    private static void doSomeThing(Object context) {
        if (context == null) {
            throw new NullPointerException("Context is null");
        }
        System.out.println("Do Something");
    }

    private static Object loadContext() {
        System.out.println("Load Context");
        return new Object();
    }
}
