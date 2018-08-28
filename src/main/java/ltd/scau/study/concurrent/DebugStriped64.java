package ltd.scau.study.concurrent;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author Weijie Wu
 */
public class DebugStriped64 {

    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((left, right) -> left + right, 233333);
        for (int i = 0; i < 32; i++) {
            accumulator.accumulate(i);
        }
        System.out.println(accumulator);
    }
}
