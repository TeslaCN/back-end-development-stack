# 进程 线程 并行 并发

### 并行
* 同时执行

### 并发
* 时间片切换

### 程序
* 指令与数据的集合

### 进程
* 指令 数据 PCB
* 程序的运行 程序的一个实例
* 系统进行资源分配与调度的基本单位

### 线程
* 共享进程拥有的资源
* 线程切换开销小

#### 内核线程
* 操作系统内核支持
* 轻量级进程LWP，与内核线程一对一
* 需要切换内核态，开销较大
* 一个进程内多个线程可以同时运行

#### 用户线程 
* 无须操作系统支持
* 自定义线程调度算法
* 无须用户/内核态切换，开销小
* 不能与处理单元映射
* **一个进程只能有一个线程运行，线程阻塞会导致进程阻塞**

