# AQS

使用AQS实现的工具：
* java.util.concurrent.locks.ReentrantLock
* java.util.concurrent.locks.ReentrantReadWriteLock
* java.util.concurrent.ThreadPoolExecutor
* java.util.concurrent.Semaphore
* java.util.concurrent.CountDownLatch
* java.util.concurrent.CyclicBarrier

使用AQS要点：
* 根据需求实现Exclusive或者Shared方法
* 管理state变量

AQS使用java.util.LockSupport实现阻塞、唤醒

JDK文档提供的使用示例，使用AQS实现简单Lock
```java
class Mutex implements Lock, java.io.Serializable {

   // Our internal helper class
   private static class Sync extends AbstractQueuedSynchronizer {
     // Reports whether in locked state
     protected boolean isHeldExclusively() {
       return getState() == 1;
     }

     // Acquires the lock if state is zero
     public boolean tryAcquire(int acquires) {
       assert acquires == 1; // Otherwise unused
       if (compareAndSetState(0, 1)) {
         setExclusiveOwnerThread(Thread.currentThread());
         return true;
       }
       return false;
     }

     // Releases the lock by setting state to zero
     protected boolean tryRelease(int releases) {
       assert releases == 1; // Otherwise unused
       if (getState() == 0) throw new IllegalMonitorStateException();
       setExclusiveOwnerThread(null);
       setState(0);
       return true;
     }

     // Provides a Condition
     Condition newCondition() { return new ConditionObject(); }

     // Deserializes properly
     private void readObject(ObjectInputStream s)
         throws IOException, ClassNotFoundException {
       s.defaultReadObject();
       setState(0); // reset to unlocked state
     }
   }

   // The sync object does all the hard work. We just forward to it.
   private final Sync sync = new Sync();

   public void lock()                { sync.acquire(1); }
   public boolean tryLock()          { return sync.tryAcquire(1); }
   public void unlock()              { sync.release(1); }
   public Condition newCondition()   { return sync.newCondition(); }
   public boolean isLocked()         { return sync.isHeldExclusively(); }
   public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
   public void lockInterruptibly() throws InterruptedException {
     sync.acquireInterruptibly(1);
   }
   public boolean tryLock(long timeout, TimeUnit unit)
       throws InterruptedException {
     return sync.tryAcquireNanos(1, unit.toNanos(timeout));
   }
}}
```

### AQS Exclusive
#### 代码流程(以java.util.concurrent.locks.ReentrantLock为例，Nonfair)

调用 lock()
```
final void lock() {
    // state == 0 即未锁定，直接独占
    if (compareAndSetState(0, 1))
        setExclusiveOwnerThread(Thread.currentThread());
    else
    // 否则调用AQS方法
        acquire(1);
}
```

lock() 锁已被获取，此时再次尝试获取资源，如果仍然失败则进入CLH队列
```
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

向CLH队列尾部添加结点，首先以简单快速的方式入列，如果此时竞争激烈导致快速入列添加失败，则进入自旋的方式入列
```
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        if (t == null) { // Must initialize
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}

private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    enq(node);
    return node;
}
```
到了acquireQueued方法，开始自旋
此时，先获取当前结点的前一结点，如果前一结点为队列头，再次尝试获取资源
* 如果成功，把当前结点设为队列头，并断开前置结点以便于GC
* 如果失败，则检查前置结点的状态，决定当前结点是否可以park
  * 前置结点waitState == SIGNAL(-1)，表示当前结点可以park()，前置结点在release()的时候会unpark()当前结点，当前结点可以放心的park()了
  * 前置结点的 waitState > 0，即 CANCELED，则表示前置结点已放弃，把当前结点加塞，直到前置结点的waitState <= 0
  * 前置结点waitState == 0 || waitState < -1，尝试把前置结点状态设为SIGNAL，此时返回acquireQueued方法自旋

于是，当前线程接下来的事情有两种情况：
1. 获取到资源，开始执行临界区代码
2. park()阻塞，等待前置结点唤醒
```
/** waitStatus value to indicate thread has cancelled */
static final int CANCELLED =  1;

/** waitStatus value to indicate successor's thread needs unparking */
static final int SIGNAL    = -1;

/** waitStatus value to indicate thread is waiting on condition */
static final int CONDITION = -2;

/**
 * waitStatus value to indicate the next acquireShared should
 * unconditionally propagate
 */
static final int PROPAGATE = -3;

/**
 * Status field, taking on only the values:
 *   SIGNAL:     The successor of this node is (or will soon be)
 *               blocked (via park), so the current node must
 *               unpark its successor when it releases or
 *               cancels. To avoid races, acquire methods must
 *               first indicate they need a signal,
 *               then retry the atomic acquire, and then,
 *               on failure, block.
 *   CANCELLED:  This node is cancelled due to timeout or interrupt.
 *               Nodes never leave this state. In particular,
 *               a thread with cancelled node never again blocks.
 *   CONDITION:  This node is currently on a condition queue.
 *               It will not be used as a sync queue node
 *               until transferred, at which time the status
 *               will be set to 0. (Use of this value here has
 *               nothing to do with the other uses of the
 *               field, but simplifies mechanics.)
 *   PROPAGATE:  A releaseShared should be propagated to other
 *               nodes. This is set (for head node only) in
 *               doReleaseShared to ensure propagation
 *               continues, even if other operations have
 *               since intervened.
 *   0:          None of the above
 *
 * The values are arranged numerically to simplify use.
 * Non-negative values mean that a node doesn't need to
 * signal. So, most code doesn't need to check for particular
 * values, just for sign.
 *
 * The field is initialized to 0 for normal sync nodes, and
 * CONDITION for condition nodes.  It is modified using CAS
 * (or when possible, unconditional volatile writes).
 */
volatile int waitStatus;
        
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}

private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
    if (ws == Node.SIGNAL)
        /*
         * This node has already set status asking a release
         * to signal it, so it can safely park.
         */
        return true;
    if (ws > 0) {
        /*
         * Predecessor was cancelled. Skip over predecessors and
         * indicate retry.
         */
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {
        /*
         * waitStatus must be 0 or PROPAGATE.  Indicate that we
         * need a signal, but don't park yet.  Caller will need to
         * retry to make sure it cannot acquire before parking.
         */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}

private final boolean parkAndCheckInterrupt() {
    LockSupport.park(this);
    return Thread.interrupted();
}
```
lock()的流程至此大致结束

Nonfair 与 Fair 的区别
* Fair实现在tryAcquire的时候会调用判断是否有前置结点
```
protected final boolean tryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        // 判断是否存在前置结点
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0)
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

unlock()流程
调用AQS的release方法，AQS调用子类实现的tryRelease方法
```
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```
```
protected final boolean tryRelease(int releases) {
    int c = getState() - releases;
    // 如果当前线程没有独占，则抛出状态异常
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    if (c == 0) {
        // state 回到 0 即代表获取的资源已释放，独占的线程设为 null 表示未被占有
        free = true;
        setExclusiveOwnerThread(null);
    }
    setState(c);
    return free;
}
```


```
private void unparkSuccessor(Node node) {
    // 获取当前结点
    int ws = node.waitStatus;
    if (ws < 0)
        compareAndSetWaitStatus(node, ws, 0);

    Node s = node.next;
    // 获取后置结点
    if (s == null || s.waitStatus > 0) {
        // 如果没有后置结点或者后置结点的 waitState > 0，即被取消或中断
        s = null;
        // 从后往前遍历CLH队列，一直找到队列最左边的待唤醒的结点
        for (Node t = tail; t != null && t != node; t = t.prev)
            if (t.waitStatus <= 0)
                s = t;
    }
    // 如果待唤醒结点存在，则唤醒
    if (s != null)
        LockSupport.unpark(s.thread);
}
```
unlock()流程至此大致结束
以上为Exclusive独占模式的AQS使用

### AQS Shared
#### 以 java.util.concurrent.Semaphore 为例

获取资源 acquire()
```
public final void acquireSharedInterruptibly(int arg)
        throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    // 尝试获取资源，如果返回值 < 0 代表资源获取失败，把线程加入队列
    if (tryAcquireShared(arg) < 0)
        doAcquireSharedInterruptibly(arg);
}
```
```
public final void acquireSharedInterruptibly(int arg)
        throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    if (tryAcquireShared(arg) < 0)
        doAcquireSharedInterruptibly(arg);
}
```

```
protected int tryAcquireShared(int acquires) {
    for (;;) {
    
        // Fair
        if (hasQueuedPredecessors())
            return -1;
            
        // Nonfair 不判断是否有前置结点
        int available = getState();
        int remaining = available - acquires;
        if (remaining < 0 ||
            compareAndSetState(available, remaining))
            return remaining;
    }
}
```

Shared入列流程和Exclusive模式相似
```
private void doAcquireShared(int arg) {
    final Node node = addWaiter(Node.SHARED);
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            if (p == head) {
                int r = tryAcquireShared(arg);
                if (r >= 0) {
                    setHeadAndPropagate(node, r);
                    p.next = null; // help GC
                    if (interrupted)
                        selfInterrupt();
                    failed = false;
                    return;
                }
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

