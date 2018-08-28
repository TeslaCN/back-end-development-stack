# 偏向锁 自旋锁 轻量级锁 重量级锁

### synchronize

偏向锁
* 在资源实例的 Mark Word 记录Thread的实例地址，即该线程获得锁

轻量级锁
* 在各线程栈分配 Lock Record
* 把资源实例的 Mark Word 指向获得锁的线程的栈内的 Lock Record，即该线程获得锁
* 未获得锁的线程自旋

重量级锁
* 操作系统级别
* 线程阻塞