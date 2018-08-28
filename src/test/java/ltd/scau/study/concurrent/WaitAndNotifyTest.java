package ltd.scau.study.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
class WaitAndNotifyTest {

    @Test
    void waitAndNotifyTest() throws InterruptedException {
        WaitAndNotify.waitAndNotify();
    }

    @Test
    void parkTest() throws InterruptedException {
        WaitAndNotify.park();
    }
}