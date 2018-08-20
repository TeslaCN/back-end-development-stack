package ltd.scau.study.concurrent;

/**
 * @author Weijie Wu
 */
public class DeadLock {

    public static void main(String[] args) {

        final Object money = new Object();
        final Object good = new Object();
        Runnable seller = () -> {
            synchronized (good) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Give me Money");
                synchronized (money) {
                    System.out.println("I got Money");
                }
            }
        };

        Runnable customer = () -> {
            synchronized (money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Give me Good");
                synchronized (good) {
                    System.out.println("I got good");
                }
            }
        };

        new Thread(seller).start();
        new Thread(customer).start();
    }
}
