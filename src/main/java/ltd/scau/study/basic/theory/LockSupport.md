# java.util.concurrent.LockSupport

### LockSupport 与 wait/notify 比较

Wait&Notify使用方式:
```
AtomicReference<Boolean> condition = new AtomicReference<>(false);
Runnable consumer = () -> {
    for (int i = 0; i < 5; i++) {
        synchronized (condition) {
            System.out.println("Wait. " + Thread.currentThread().getName());
            while (!condition.get()) {
                try {
                    condition.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Got. " + Thread.currentThread().getName());
            condition.set(false);
        }
    }
};
Runnable producer = () -> {
    for (; ; ) {
        try {
            Thread.sleep((long) (100 + Math.random() * 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (condition) {
            System.out.println("Nofity");
            condition.set(true);
            //condition.notify();
            condition.notifyAll();
        }
    }
};

Thread p = new Thread(producer);
Thread c = new Thread(consumer);
p.start();
c.start();
```
wait()&notify()/notifyAll()注意事项：
* 如果notify()不慎先于wait()调用，有可能会造成wait无法被唤醒
* 必须在synchronize代码块中调用

LockSupport使用:
```
AtomicReference<Boolean> condition = new AtomicReference<>(false);
Runnable consumer = () -> {
    for (int i = 0; i < 3; i++) {
        System.out.println("Park. " + Thread.currentThread().getName());
        while (!condition.get()) {
            LockSupport.park();
        }
        System.out.println("Got. " + Thread.currentThread().getName());
        condition.set(false);
    }
};
Thread c = new Thread(consumer);

Runnable producer = () -> {
    for (; ; ) {
        try {
            Thread.sleep((long) (100 + Math.random() * 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Unpark");
        condition.set(true);
        LockSupport.unpark(c);
    }
};
Thread p = new Thread(producer);
p.start();
c.start();
```
LockSupport:
* 类似于Semaphore(1)
* unpark()可以先于park()调用
