# java.util.concurrent.atomic.*
## 原子操作类

java.util.concurrent.atomic.*
* java.util.concurrent.atomic.AtomicBoolean
* java.util.concurrent.atomic.AtomicInteger
* java.util.concurrent.atomic.AtomicLong
* java.util.concurrent.atomic.AtomicIntegerArray
* java.util.concurrent.atomic.AtomicIntegerFieldUpdater
* java.util.concurrent.atomic.AtomicLongArray
* java.util.concurrent.atomic.AtomicLongFieldUpdater
* java.util.concurrent.atomic.AtomicMarkableReference
* java.util.concurrent.atomic.AtomicReference
* java.util.concurrent.atomic.AtomicReferenceArray
* java.util.concurrent.atomic.AtomicReferenceFieldUpdater
* java.util.concurrent.atomic.AtomicStampedReference
* java.util.concurrent.atomic.DoubleAccumulator
* java.util.concurrent.atomic.DoubleAdder
* java.util.concurrent.atomic.LongAccumulator
* java.util.concurrent.atomic.LongAdder
* java.util.concurrent.atomic.Striped64


### java.util.concurrent.atomic.Striped64
* striped 即把连续的数据分段，在此处为利用多个变量提高竞争激烈时的并发性能
* 

DEBUG:
以 **java.util.concurrent.atomic.LongAccumulator**实现为例
1. 利用断点，步入DoubleAccumulator内部
2. Evaluate expression改变变量值
3. 修改变量值导致调用 casBase 失败后，即认为发生竞争，触发doubleAccumulate方法
  * 从此只利用Cell[]存储数据，原本base值不再修改
  * 获取值时，对Cell[]做统计并加上base的数值得出结果

调用accumulate方法
```
public void accumulate(long x) {
    Cell[] as; long b, v, r; int m; Cell a;
    if ((as = cells) != null || // Cell[] 为空
        (r = function.applyAsLong(b = base, x)) != b && !casBase(b, r)) { cas base的值失败
        boolean uncontended = true; // 即发生冲突
        if (as == null || (m = as.length - 1) < 0 ||
            (a = as[getProbe() & m]) == null || // cell不为空
            !(uncontended =
              (r = function.applyAsLong(v = a.value, x)) == v ||
              a.cas(v, r))) // 尝试cas相应cell的value
            longAccumulate(x, function, uncontended); // 未初始化或casCell仍然失败，进入自旋
    }
}
```


```
final void longAccumulate(long x, LongBinaryOperator fn,
                          boolean wasUncontended) {
    int h;
    if ((h = getProbe()) == 0) {
        ThreadLocalRandom.current(); // force initialization
        h = getProbe();
        wasUncontended = true;
    }
    boolean collide = false;                // True if last slot nonempty
    for (;;) {
        Cell[] as; Cell a; int n; long v;
        if ((as = cells) != null && (n = as.length) > 0) {
            if ((a = as[(n - 1) & h]) == null) {
                if (cellsBusy == 0) {       // Try to attach new Cell
                    Cell r = new Cell(x);   // Optimistically create
                    if (cellsBusy == 0 && casCellsBusy()) {
                        boolean created = false;
                        try {               // Recheck under lock
                            Cell[] rs; int m, j;
                            if ((rs = cells) != null &&
                                (m = rs.length) > 0 &&
                                rs[j = (m - 1) & h] == null) {
                                rs[j] = r;
                                created = true;
                            }
                        } finally {
                            cellsBusy = 0;
                        }
                        if (created)
                            break;
                        continue;           // Slot is now non-empty
                    }
                }
                collide = false;
            }
            else if (!wasUncontended)       // CAS already known to fail
                wasUncontended = true;      // Continue after rehash
            else if (a.cas(v = a.value, ((fn == null) ? v + x :
                                         fn.applyAsLong(v, x))))
                break;
            else if (n >= NCPU || cells != as)
                collide = false;            // At max size or stale
            else if (!collide)
                collide = true;
            else if (cellsBusy == 0 && casCellsBusy()) {
                try {
                    if (cells == as) {      // Expand table unless stale
                        Cell[] rs = new Cell[n << 1];
                        for (int i = 0; i < n; ++i)
                            rs[i] = as[i];
                        cells = rs;
                    }
                } finally {
                    cellsBusy = 0;
                }
                collide = false;
                continue;                   // Retry with expanded table
            }
            h = advanceProbe(h);
        }
        else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
            boolean init = false;
            try {                           // Initialize table
                if (cells == as) {
                    Cell[] rs = new Cell[2];
                    rs[h & 1] = new Cell(x);
                    cells = rs;
                    init = true;
                }
            } finally {
                cellsBusy = 0;
            }
            if (init)
                break;
        }
        else if (casBase(v = base, ((fn == null) ? v + x :
                                    fn.applyAsLong(v, x))))
            break;                          // Fall back on using base
    }
}
```
