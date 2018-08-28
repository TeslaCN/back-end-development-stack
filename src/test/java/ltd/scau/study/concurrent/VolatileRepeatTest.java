package ltd.scau.study.concurrent;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weijie Wu
 */
class VolatileRepeatTest {

    @RepeatedTest(2000)
    void runTest() {
        VolatileTest.runTest();
    }
}