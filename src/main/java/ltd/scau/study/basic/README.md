#1、基础篇

###1.1、Java基础 

面向对象的特征：继承、封装和多态

final, finally, finalize 的区别

[Exception、Error、运行时异常与一般异常有何异同](theory/Exception&Error.md)

[请写出5种常见到的runtime exception](theory/RuntimeException.md)

int 和 Integer 有什么区别，Integer的值缓存范围

[包装类，装箱和拆箱](theory/Wrapper.md)

String、StringBuilder、StringBuffer

重载和重写的区别

抽象类和接口有什么区别

说说反射的用途及实现

说说自定义注解的场景及实现

[HTTP请求的GET与POST方式的区别](theory/Get&Post.md)

Session与Cookie区别

列出自己常用的JDK包

MVC设计思想 

equals与==的区别

hashCode和equals方法的区别与联系

什么是Java序列化和反序列化，如何实现Java序列化？或者请解释Serializable 接口的作用

[Object类中常见的方法，为什么wait  notify会放在Object里边？](theory/Object.md)

Java的平台无关性如何体现出来的

JDK和JRE的区别

Java 8有哪些新特性

###1.2、Java常见集合

List 和 Set 区别

Set和hashCode以及equals方法的联系

List 和 Map 区别

Arraylist 与 LinkedList 区别

ArrayList 与 Vector 区别

HashMap 和 Hashtable 的区别

HashSet 和 HashMap 区别

HashMap 和 ConcurrentHashMap 的区别

HashMap 的工作原理及代码实现，什么时候用到红黑树

多线程情况下HashMap死循环的问题

HashMap出现Hash DOS攻击的问题

ConcurrentHashMap 的工作原理及代码实现，如何统计所有的元素个数

手写简单的HashMap

看过那些Java集合类的源码

###1.3、进程和线程

线程和进程的概念、并行和并发的概念

创建线程的方式及实现

进程间通信的方式

说说 CountDownLatch、CyclicBarrier 原理和区别

说说 Semaphore 原理

说说 Exchanger 原理

ThreadLocal 原理分析，ThreadLocal为什么会出现OOM，出现的深层次原理

讲讲线程池的实现原理

线程池的几种实现方式

线程的生命周期，状态是如何转移的

可参考：《Java多线程编程核心技术》
###1.4、锁机制

说说线程安全问题，什么是线程安全，如何保证线程安全

重入锁的概念，重入锁为什么可以防止死锁

产生死锁的四个条件（互斥、请求与保持、不剥夺、循环等待）

如何检查死锁（通过jConsole检查死锁）

volatile 实现原理（禁止指令重排、刷新内存）

synchronized 实现原理（对象监视器）

synchronized 与 lock 的区别

AQS同步队列

CAS无锁的概念、乐观锁和悲观锁

常见的原子操作类

什么是ABA问题，出现ABA问题JDK是如何解决的

乐观锁的业务场景及实现方式

Java 8并法包下常见的并发类

偏向锁、轻量级锁、重量级锁、自旋锁的概念

可参考：《Java多线程编程核心技术》

###1.5、JVM

JVM运行时内存区域划分

内存溢出OOM和堆栈溢出SOE的示例及原因、如何排查与解决

如何判断对象是否可以回收或存活

常见的GC回收算法及其含义

常见的JVM性能监控和故障处理工具类：jps、jstat、jmap、jinfo、jconsole等

JVM如何设置参数

JVM性能调优

类加载器、双亲委派模型、一个类的生命周期、类是如何加载到JVM中的

类加载的过程：加载、验证、准备、解析、初始化

强引用、软引用、弱引用、虚引用

Java内存模型JMM

###1.6、设计模式

常见的设计模式

设计模式的的六大原则及其含义

常见的单例模式以及各种实现方式的优缺点，哪一种最好，手写常见的单利模式

设计模式在实际场景中的应用

Spring中用到了哪些设计模式

MyBatis中用到了哪些设计模式

你项目中有使用哪些设计模式

说说常用开源框架中设计模式使用分析

动态代理很重要！！！

###1.7、数据结构

树（二叉查找树、平衡二叉树、红黑树、B树、B+树）

深度有限算法、广度优先算法

克鲁斯卡尔算法、普林母算法、迪克拉斯算法

什么是一致性Hash及其原理、Hash环问题

常见的排序算法和查找算法：快排、折半查找、堆排序等

###1.8、网络/IO基础

BIO、NIO、AIO的概念

什么是长连接和短连接

Http1.0和2.0相比有什么区别，可参考《Http 2.0》

Https的基本概念

三次握手和四次挥手、为什么挥手需要四次

从游览器中输入URL到页面加载的发生了什么？可参考《从输入URL到页面加载发生了什么》