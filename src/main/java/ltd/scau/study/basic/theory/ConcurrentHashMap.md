# java.util.concurrent.ConcurrentHashMap

### 1.7
* 分段锁结构
  * 多个段，每个段与一个HashMap的结构类似
 
size()
* for统计所有Segment的元素个数，如果统计过程中发生增删，重新统计
* 如果竞争激烈，统计多次失败，则锁定整个Map

### 1.8
* 维护元素个数的方式（参考[java.util.concurrent.atomic.Striped64](atomic.md))
  * long volatile baseCount 记录元素个数（无竞争）
  * CounterCell counterCells[]（竞争）
  
* Node[]数组+红黑树
* 对操作的Node[]加锁
```
@sun.misc.Contended static final class CounterCell {
    volatile long value;
    CounterCell(long x) { value = x; }
}
```
* addCount(long x, int check) 增删元素个数
```
private final void addCount(long x, int check) {
    CounterCell[] as; long b, s;
    if ((as = counterCells) != null || // 如果 CounterCell 未初始化
        !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) { // baseCount CAS设置失败
        CounterCell a; long v; int m;
        boolean uncontended = true;
        if (as == null || (m = as.length - 1) < 0 ||
            (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
            !(uncontended =
              U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
            fullAddCount(x, uncontended);
            return;
        }
        if (check <= 1)
            return;
        s = sumCount();
    }
    if (check >= 0) {
        Node<K,V>[] tab, nt; int n, sc;
        while (s >= (long)(sc = sizeCtl) && (tab = table) != null &&
               (n = tab.length) < MAXIMUM_CAPACITY) {
            int rs = resizeStamp(n);
            if (sc < 0) {
                if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                    sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
                    transferIndex <= 0)
                    break;
                if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
                    transfer(tab, nt);
            }
            else if (U.compareAndSwapInt(this, SIZECTL, sc,
                                         (rs << RESIZE_STAMP_SHIFT) + 2))
                transfer(tab, null);
            s = sumCount();
        }
    }
}
```
* size() 调用 sumCount()
```
final long sumCount() {
    CounterCell[] as = counterCells; CounterCell a;
    long sum = baseCount;
    if (as != null) {
        for (int i = 0; i < as.length; ++i) {
            if ((a = as[i]) != null)
                sum += a.value;
        }
    }
    return sum;
}
```
