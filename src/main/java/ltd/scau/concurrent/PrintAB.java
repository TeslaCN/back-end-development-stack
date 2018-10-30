package ltd.scau.concurrent;

/**
 * @author Weijie Wu
 */
public class PrintAB {

    private static final Object o = new Object();

    private static volatile int num = 0;

    private static final Runnable a = () -> {
        for (; ; ) {
            synchronized (o) {
                System.out.println("A in");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while ((num & 1) == 1) {
                    System.out.println("A Wait");
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A Waked Up");
                }
                num++;
                System.out.println("aaaaaaaaaaaaaaa");
                o.notify();
                System.out.println("A Notify");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A Exiting");
            }
        }
    };

    private static final Runnable b = () -> {
        for (; ; ) {
            synchronized (o) {
                System.out.println("B in");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while ((num & 1) == 0) {
                    System.out.println("B Wait");
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B Waked up");
                }
                num++;
                System.out.println("bbbbbbbbbbbbbbbbb");
                o.notify();
                System.out.println("B Notify");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B Exit");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}
