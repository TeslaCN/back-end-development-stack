package ltd.scau.study.concurrent;

import java.util.concurrent.Exchanger;

/**
 * @author Weijie Wu
 */
public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Runnable seller = () -> {
            int amount = -100;
            System.out.println("Seller: " + amount);
            try {
                amount = exchanger.exchange(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Seller exchanged: " + amount);
        };

        Runnable customer = () -> {
            int money = 998;
            System.out.println("Customer: " + money);
            try {
                money = exchanger.exchange(money);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Customer exchanged: " + money);
        };

        new Thread(seller).start();
        Thread.sleep(3000);
        new Thread(customer).start();
    }
}
