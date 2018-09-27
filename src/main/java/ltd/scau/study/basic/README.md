# 1、基础篇

### 1.1、Java基础 

1. 面向对象的特征：继承、封装和多态

2. final, finally, finalize 的区别

3. [Exception、Error、运行时异常与一般异常有何异同](theory/Exception&Error.md)

4. [请写出5种常见到的runtime exception](theory/RuntimeException.md)

5. [int 和 Integer 有什么区别，Integer的值缓存范围](theory/Wrapper.md)

6. [包装类，装箱和拆箱](theory/Wrapper.md)

7. String、StringBuilder、StringBuffer

8. 重载和重写的区别

9. 抽象类和接口有什么区别

10. 说说反射的用途及实现

11. 说说自定义注解的场景及实现

12. [HTTP请求的GET与POST方式的区别](theory/Get&Post.md)

13. Session与Cookie区别

14. 列出自己常用的JDK包

15. MVC设计思想 

16. equals与==的区别

17. hashCode和equals方法的区别与联系

18. [什么是Java序列化和反序列化，如何实现Java序列化？或者请解释Serializable 接口的作用](theory/Serializable.md)

19. [Object类中常见的方法，为什么wait  notify会放在Object里边？](theory/Object.md)

20. Java的平台无关性如何体现出来的

21. JDK和JRE的区别

22. Java 8有哪些新特性

### 1.2、Java常见集合

List 和 Set 区别

Set和hashCode以及equals方法的联系

List 和 Map 区别

Arraylist 与 LinkedList 区别

ArrayList 与 Vector 区别

HashMap 和 Hashtable 的区别

HashSet 和 HashMap 区别

HashMap 和 ConcurrentHashMap 的区别

[HashMap 的工作原理及代码实现，什么时候用到红黑树](theory/HashMap.md)

[多线程情况下HashMap死循环的问题](theory/多线程HashMap问题.md)

[HashMap出现Hash DOS攻击的问题](practice/HashCollision.java)

[ConcurrentHashMap 的工作原理及代码实现，如何统计所有的元素个数](theory/ConcurrentHashMap.md)

[手写简单的HashMap](practice/MyHashMap.java)

看过那些Java集合类的源码

### 1.3、进程和线程

[线程和进程的概念、并行和并发的概念](theory/Process&Thread.md)

创建线程的方式及实现

进程间通信的方式
* 管道、Socket等

说说 CountDownLatch、CyclicBarrier 原理和区别

[说说 Semaphore 原理](theory/aqs.md)

说说 Exchanger 原理

[ThreadLocal 原理分析，ThreadLocal为什么会出现OOM，出现的深层次原理](theory/ThreadLocal.md)

[讲讲线程池的实现原理](theory/ThreadPoolExecutor.md)

线程池的几种实现方式

[线程的生命周期，状态是如何转移的](theory/Thread.md)

可参考：《Java多线程编程核心技术》

### 1.4、锁机制

说说线程安全问题，什么是线程安全，如何保证线程安全
* 多线程的运行结果和中间变量与预期相符，与单线程运行产生的结果相同

重入锁的概念，重入锁为什么可以防止死锁
* 当前线程获取锁后，可以再次获取锁

[产生死锁的四个条件（互斥、请求与保持、不剥夺、循环等待）](../concurrent/DeadLock.java)

[如何检查死锁（通过jConsole检查死锁）](theory/jconsole.md)

[volatile 实现原理（禁止指令重排、刷新内存）](theory/volatile.md)

[synchronized 实现原理（对象监视器）](theory/monitor.md)

[synchronized 与 lock 的区别](theory/synchronize&lock.md)

[LockSupport](theory/LockSupport.md)

[AQS同步队列](theory/aqs.md)

[CAS无锁的概念、乐观锁和悲观锁](theory/cas&lock.md)

[常见的原子操作类](theory/atomic.md)

什么是ABA问题，出现ABA问题JDK是如何解决的
* java.util.concurrent.AtomicMarkableReference
* java.util.concurrent.AtomicStampedReference

乐观锁的业务场景及实现方式

Java 8并法包下常见的并发类

[偏向锁、轻量级锁、重量级锁、自旋锁的概念](theory/jdk_lock.md)

可参考：《Java多线程编程核心技术》

### 1.5、JVM

[JVM运行时内存区域划分](theory/jmm.md)

内存溢出OOM和堆栈溢出SOE的示例及原因、如何排查与解决

如何判断对象是否可以回收或存活

常见的GC回收算法及其含义

常见的JVM性能监控和故障处理工具类：jps、jstat、jmap、jinfo、jconsole等

JVM如何设置参数

JVM性能调优

[类加载器、双亲委派模型、一个类的生命周期、类是如何加载到JVM中的](theory/LoadClass.md)

[类加载的过程：加载、验证、准备、解析、初始化](theory/LoadClass.md)

[强引用、软引用、弱引用、虚引用](theory/Reference.md)

[Java内存模型JMM](theory/jmm.md)

### 1.6、设计模式

常见的设计模式

设计模式的的六大原则及其含义

常见的单例模式以及各种实现方式的优缺点，哪一种最好，手写常见的单例模式

设计模式在实际场景中的应用

Spring中用到了哪些设计模式

MyBatis中用到了哪些设计模式

你项目中有使用哪些设计模式

说说常用开源框架中设计模式使用分析

动态代理很重要！！！

### 1.7、数据结构

树（二叉查找树、平衡二叉树、红黑树、B树、B+树）

深度优先算法、广度优先算法

克鲁斯卡尔算法、普林母算法、迪克拉斯算法

什么是一致性Hash及其原理、Hash环问题

常见的排序算法和查找算法：快排、折半查找、堆排序等

### 1.8、网络/IO基础

BIO、NIO、AIO的概念

什么是长连接和短连接

Http1.0和2.0相比有什么区别，可参考《Http 2.0》

Https的基本概念

三次握手和四次挥手、为什么挥手需要四次

从游览器中输入URL到页面加载的发生了什么？可参考《从输入URL到页面加载发生了什么》


# 二
### 一、Java相关

Arraylist与LinkedList默认空间是多少；

Arraylist与LinkedList区别与各自的优势List 和 Map 区别；

谈谈HashMap，哈希表解决hash冲突的方法；

为什么要重写hashcode()和equals()以及他们之间的区别与关系；

Object的hashcode()是怎么计算的？

若hashcode方法永远返回1或者一个常量会产生什么结果？

Java Collections和Arrays的sort方法默认的排序方法是什么；

引用计数法与GC Root可达性分析法区别；

浅拷贝和深拷贝的区别；

String s="abc"和String s=new String("abc")区别；

HashSet方法里面的hashcode存在哪，如果重写equals不重写hashcode会怎么样？

反射的作用与实现原理；

Java中的回调机制；

模板方法模式；

开闭原则说一下；

发布/订阅使用场景；

KMP算法（一种改进的字符串匹配算法）；

JMM里边的原子性、可见性、有序性是如何体现出来的，JMM中内存屏障是什么意思，

### 二、多线程

AtomicInteger底层实现原理；

synchronized与ReentrantLock哪个是公平锁；

CAS机制会出现什么问题；

用过并发包下边的哪些类；

一个线程连着调用start两次会出现什么情况？
* java.lang.IllegalThreadStateException

wait方法能不能被重写，wait能不能被中断；

线程池的实现？四种线程池？重要参数及原理？任务拒接策略有哪几种？

线程状态以及API怎么操作会发生这种转换；

常用的避免死锁方法；

### 三、JVM

Minor GC与Full GC分别在什么时候发生？什么时候触发Full GC;

GC收集器有哪些？CMS收集器与G1收集器的特点。

Java在什么时候会出现内存泄漏；

Java中的大对象如何进行存储；

rt.jar被什么类加载器加载，什么时间加载；

自己写的类被什么加载，什么时间加载；

自己写的两个不同的类是被同一个类加载器加载的吗？为什么？

为什么新生代内存需要有两个Survivor区？

几种常用的内存调试工具：jmap、jstack、jconsole；

G1停顿吗，CMS回收步骤，CMS为什么会停顿，停顿时间；

栈主要存的数据是什么，堆呢？

堆分为哪几块，比如说新生代老生代，那么新生代又分为什么？

软引用和弱引用的使用场景（软引用可以实现缓存，弱引用可以用来在回调函数中防止内存泄露）；

### 四、数据库

[数据库索引，什么是全文索引，全文索引中的倒排索引是什么原理；](database/index.md)

什么是组合索引，组合索引什么时候会失效；（复合索引）

数据库最佳左前缀原则是什么？
* 带头大哥不能死，中间兄弟不能断

数据库的三大范式；
1. 原子性
2. 数据冗余
3. 传递性

悲观锁和乐观锁的原理和应用场景；

左连接、右连接、内连接、外连接、交叉连接、笛卡儿积等；

一般情况下数据库宕机了如何进行恢复（什么是Write Ahead Log机制，什么是Double Write机制，什么是Check Point）；

什么是redo日志、什么是undo日志；

数据库中的隔离性是怎样实现的；原子性、一致性、持久性又是如何实现的；

关系型数据库和非关系型数据库区别；

数据库死锁如何解决；

[MySQL并发情况下怎么解决（通过事务、隔离级别、锁）](theory/MySQL_Concurrent.md)

MySQL中的MVCC机制是什么意思，根据具体场景，MVCC是否有问题；

MySQL数据库的隔离级别，以及如何解决幻读；

### 五、缓存服务器

Redis中zSet跳跃表问题；

Redis的set的应用场合？

Redis高级特性了解吗？

Redis的pipeline有什么用处？

Redis集群宕机如何处理，怎么样进行数据的迁移；

Redis的集群方案；

Redis原子操作怎么用比较好；

Redis过期策略是怎么实现的呢？

### 六、SSM相关

Spring中@Autowired和@Resource注解的区别？

Spring声明一个 bean 如何对其进行个性化定制；

MyBatis有什么优势；

MyBatis如何做事务管理；

### 七、操作系统

Linux静态链接和动态链接；

什么是IO多路复用模型（select、poll、epoll）；

Linux中的grep管道用处？Linux的常用命令？

操作系统中虚拟地址、逻辑地址、线性地址、物理地址的概念及区别；

内存的页面置换算法；

内存的页面置换算法；

进程调度算法，操作系统是如何调度进程的；

父子进程、孤儿进程、僵死进程等概念；

fork进程时的操作；

kill用法，某个进程杀不掉的原因（僵死进程；进入内核态，忽略kill信号）；

系统管理命令（如查看内存使用、网络情况）；

find命令、awk使用；

Linux下排查某个死循环的线程；

### 八、网络相关

数据链路层是做什么的?

数据链路层的流量控制？

网络模型的分层、IP和Mac地址在那个层、TCP和HTTP分别在那个层；

TCP滑动窗口；

TCP为什么可靠；

TCP的同传，拆包与组装包是什么意思；

Https和Http有什么区别；

Http 为什么是无状态的；

TCP三次握手，为什么不是三次，为什么不是四次；

TCP的拥塞控制、流量控制详细说明？

Http1.0和Http2.0的区别；

两个不同ip地址的计算机之间如何通信；

地址解析协议ARP；

OSI七层模型分别对应着五层模型的哪一部分；

TCP三次握手数据丢失了怎么办？那如果后面又找到了呢？

### 九、分布式相关

消息队列使用的场景介绍和作用（应用耦合、异步消息、流量削锋等）；

如何解决消息队列丢失消息和重复消费问题；

Kafka使用过吗，什么是幂等性？怎么保证一致性，持久化怎么做，分区partition的理解，LEO是什么意思，如何保证多个partition之间数据一致性的（ISR机制），为什么Kafka可以这么快（基于磁盘的顺序读写）；

异步队列怎么实现；

你项目的并发是多少？怎么解决高并发问题？单机情况下Tomcat的并发大概是多少，MySQL的并发大致是多少？

什么是C10K问题；

高并发情况下怎么办；

分布式理论，什么是CAP理论，什么是Base理论，什么是Paxos理论；

分布式协议的选举算法；

说一下你对微服务的理解，与SOA的区别；

Dubbo的基本原理，RPC，支持哪些通信方式，服务的调用过程；

Dubbo如果有一个服务挂掉了怎么办；

分布式事务，操作两个表不在一个库，如何保证一致性。

分布式系统中，每台机器如何产生一个唯一的随机值；

系统的量级、pv、uv等；

什么是Hash一致性算法？分布式缓存的一致性，服务器如何扩容（哈希环）；

正向代理、反向代理；

什么是客户端负载均衡策略、什么是服务器端负载均衡策略；

如何优化Tomcat，常见的优化方式有哪些；

Nginx的Master和Worker，Nginx是如何处理请求的；

### 十、系统设计相关

如何防止表单重复提交（Token令牌环等方式）；

有一个url白名单，需要使用正则表达式进行过滤，但是url量级很大，大概亿级，那么如何优化正则表达式？如何优化亿级的url匹配呢？

常见的Nginx负载均衡策略；已有两台Nginx服务器了，倘若这时候再增加一台服务器，采用什么负载均衡算法比较好？

扫描二维码登录的过程解析；

如何设计一个生成唯一UUID的算法？

实现一个负载均衡的算法，服务器资源分配为70%、20%、10%；

有三个线程T1 T2 T3，如何保证他们按顺序执行；

三个线程循环输出ABCABCABC....

### 十一、安全相关

什么是XSS攻击，XSS攻击的一般表现形式有哪些？如何防止XSS攻击；


一、基础题

怎么解决Hash冲突；（开放地址法、链地址法、再哈希法、建立公共溢出区等）

写出一个必然会产生死锁的伪代码；

Spring IoC涉及到的设计模式；（工厂模式、单利模式。。）

toString()方法什么情况下需要重写；

判断对象相等时，什么情况下只需要重写 equals()，什么情况下需要重写 equals(),hashcode()？

Set内存放的元素为什么不可以重复，内部是如何保证和实现的？

如何保证分布式缓存的一致性(分布式缓存一致性hash算法?)？分布式session实现？

Java 8流式迭代的好处？

项目中用到的JDK的哪些特性？

说一下TreeMap的实现原理？红黑树的性质？红黑树遍历方式有哪些？如果key冲突如何解决？setColor()方法在什么时候用？什么时候会进行旋转和颜色转换？

Spring的bean的创建时机？依赖注入的时机？

ArrayList和LinkList的删除一个元素的时间复杂度；（ArrayList是O(N)，LinkList是O(1)）；

CopyOnWriteArrayList是什么；

序列化和反序列化底层如何实现的（ObjectOutputStream 、ObjectInputStream、 readObject  writeObject）；

如何调试多线程的程序；

一个线程连着调用start两次会出现什么情况？（由于状态只有就绪、阻塞、执行，状态是无法由执行转化为执行的，所以会报不合法的状态！）

HashMap在什么时候时间复杂度是O（1），什么时候是O（n），什么时候又是O（logn）；

wait方法能不能被重写？（wait是final类型的，不可以被重写，不仅如此，notify和notifyall都是final类型的），wait能不能被中断；

一个Controller调用两个Service，这两Service又都分别调用两个Dao，问其中用到了几个数据库连接池的连接？

二、网络基础

HTTP、TCP、UDP的区别和联系；

TCP和UDP各自的优势，知道哪些使用UDP协议的成功案例；

TCP和UDP各用了底层什么协议；

单个UDP报文最大容量；

单个TCP报文最大容量；

TCP报头格式、UDP报头格式；

Server遭遇SYN Flood应当怎么处理；

Web开发中如何防范XSS？

拆包和粘包的问题，如何解决，如果我们的包没有固定长度的话，我们的应用程序应该如何解决；

三、操作系统

为什么要内存对齐；

为什么会有大端小端，htol这一类函数的作用；

top显示出来的系统信息都是什么含义；（重要！）

Linux地址空间，怎么样进行寻址的；

Linux如何查找目录或者文件的；

四、分布式其他

分库与分表带来的分布式困境与应对之策；

Solr如何实现全天24小时索引更新；

五、Redis

Redis插槽的分配（key的有效部分使用CRC16算法计算出哈希值，再将哈希值对16384取余，得到插槽值）;

Redis主从是怎么选取的（一种是主动切换，另一种是使用sentinel自动方式）;

Redis复制的过程;

Redis队列应用场景；

Redis主节点宕机了怎么办，还有没有同步的数据怎么办;

六、系统设计开放性题目

秒杀系统设计，超卖怎么搞;

你们的图片时怎么存储的，对应在数据库中时如何保存图片的信息的？

假如成都没有一座消防站，现在问你要建立几座消防站，每个消防站要配多少名消防官兵，多少辆消防车，请你拿出一个方案；

基于数组实现一个循环阻塞队列；

常见的ipv4地址的展现形式如“168.0.0.1”，请实现ip地址和int类型的相互转换。（使用位移的方式）

现网某个服务部署在多台Liunx服务器上，其中一台突然出现CPU 100%的情况，而其他服务器正常，请列举可能导致这种情况发生的原因？如果您遇到这样的情况，应如何定位？内存？CPU？发布？debug？请求量？

七、大数据量问题（后边会有专题单独讨论）

给定a、b两个文件，各存放50亿个url，每个url各占64字节，内存限制是4G，让你找出a、b文件共同的url？

海量日志数据，提取出某日访问百度次数最多的那个IP；

一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前10个词，请给出思想，给出时间复杂度分析。

此话题后边会有专门的文章探讨，如果有等不及的小伙伴，可以移步参考：

1、https://blog.csdn.net/v_july_v/article/details/6279498

2、https://blog.csdn.net/v_july_v/article/details/7382693

八、逻辑思维题

有两根粗细均匀的香（烧香拜佛的香），每一根烧完都花一个小时，怎么样能够得到15min？

假定你有8个撞球，其中有1个球比其他的球稍重,如果只能利用天平来断定哪一个球重,要找到较重的球,要称几次?（2次）；

实验室里有1000个一模一样的瓶子，但是其中的一瓶有毒。可以用实验室的小白鼠来测试哪一瓶是毒药。如果小白鼠喝掉毒药的话，会在一个星期的时候死去，其他瓶子里的药水没有任何副作用。
请问最少用多少只小白鼠可以在一个星期以内查出哪瓶是毒药；（答案是10只）

假设有一个池塘，里面有无穷多的水。现有2个空水壶，容积分别为5升和6升。问题是如何只用这2个水壶从池塘里取得3升的水；

一、Java基础

为什么JVM调优经常会将-Xms和-Xmx参数设置成一样；
* 大小一样则避免了伸缩堆的开销

Java线程池的核心属性以及处理流程；

Java内存模型，方法区存什么；

CMS垃圾回收过程；

Full GC次数太多了，如何优化；

直接内存如何管理的；

Java线程池的几个参数的意义和实现机制；

Java线程池使用无界任务队列和有界任务队列的优劣对比；

CountDownLatch和CyclicBarrier的区别；

Java中有哪些同步方案（重量级锁、显式锁、并发容器、并发同步器、CAS、volatile、AQS等）

如果你的项目出现了内存泄露，怎么监控这个问题呢；

标记清除和标记整理的区别和优缺点，为何标记整理会发生stop the world；

线程池，如何根据CPU的核数来设计线程大小，如果是计算机密集型的呢，如果是IO密集型的呢？

让你设计一个cache如何设计；

String中hashcode是怎么实现的；

JDK中哪些实现了单例模式？

多个线程同时读写，读线程的数量远远⼤于写线程，你认为应该如何解决并发的问题？你会选择加什么样的锁？

线程池内的线程如果全部忙，提交⼀个新的任务，会发⽣什么？队列全部塞满了之后，还是忙，再提交会发⽣什么？

synchronized关键字锁住的是什么东西？在字节码中是怎么表示的？在内存中的对象上表现为什么？

wait/notify/notifyAll⽅法需不需要被包含在synchronized块中？这是为什么？

ExecutorService你一般是怎么⽤的？是每个Service放一个还是个项目放一个？有什么好处？

二、数据库

InnoDB的插入缓冲和两次写的概率和意义；

如果建了⼀个单列索引，查询的时候查出2列，会⽤到这个单列索引吗？（会用到）

如果建了⼀个包含多个列的索引，查询的时候只⽤了第⼀列，能不能⽤上这个索引？查三列呢？

接上题，如果where条件后⾯带有⼀个 i + 5 < 100 会使⽤到这个索引吗？

like %aaa%会使⽤索引吗? like aaa%呢?

drop、truncate、delete的区别？

平时你们是怎么监控数据库的? 慢SQL是怎么排查的？（慢查询日志）

你们数据库是否⽀持emoji表情，如果不⽀持，如何操作?选择什么编码方式？如果支持一个表情占几个字节?(utf8mb4)；

如果查询很慢，你会想到的第⼀个⽅式是什么？（数据库索引）

三、Linux基础

Linux下可以在/proc目录下可以查看CPU的核心数等；cat /proc/下边会有很多系统内核信息可供显示； 

说一下栈的内存是怎么分配的；

Linux各个目录有了解过吗？/etc、/bin、/dev、/lib、/sbin这些常见的目录主要作用是什么？

说一下栈帧的内存是怎么分配的；

Linux下排查某个死循环的线程；

动态链接和静态链接的区别；

进程的内存分布；

如何查找一个进程打开所有的文件；

说一下常使用的协议及其对应的端口；

为什么会有内核态，保护模式你知道吗?

文件是怎么在磁盘上存储的？

有了进程为何还要线程呢，不同进程和线程他们之间有什么不同。
（进程是资源管理的最小单位，线程是程序执行的最小单位。在操作系统设计上，从进程演化出线程，最主要的目的就是更好的支持SMP以及减小（进程/线程）上下文切换开销。）

InnoDB聚集索引B+树叶子节点和磁盘什么顺序相同;

文件系统，进程管理和调度，内存管理机制、虚地址保护模式；

四、网络基础

HTTP1.0和HTTP1.1的区别；

DHCP如何实现分配IP的； 
发现阶段（DHCP客户端在网络中广播发送DHCP DISCOVER请求报文，发现DHCP服务器，请求IP地址租约）
提供阶段（DHCP服务器通过DHCP OFFER报文向DHCP客户端提供IP地址预分配）、选择阶段（DHCP客户端通过DHCP REQUEST报文确认选择第一个DHCP服务器为它提供IP地址自动分配服务）
确认阶段（被选择的DHCP服务器通过DHCP ACK报文把在DHCP OFFER报文中准备的IP地址租约给对应DHCP客户端）

OSI七层模型，每层都说下自己的理解和知道的，说的越多越好；

五、框架相关

Servlet如何保证单例模式,可不可以编程多例的哪？

Dubbo请求流程以及原理；

Spring框架如何实现事务的；

如果一个接⼝有2个不同的实现, 那么怎么来Autowire一个指定的实现？(可以使用Qualifier注解限定要注入的Bean，也可以使用Qualifier和Autowire注解指定要获取的bean，也可以使用Resource注解的name属性指定要获取的Bean)

Spring框架中需要引用哪些jar包，以及这些jar包的用途；

Spring Boot没有放到web容器⾥为什么能跑HTTP服务？

Spring中循环注入是什么意思，可不可以解决，如何解决；

Spring的声明式事务 @Transaction注解⼀般写在什么位置? 抛出了异常会⾃动回滚吗？有没有办法控制不触发回滚?

MyBatis怎么防止SQL注入；

Tomcat本身的参数你⼀般会怎么调整？

了解哪几种序列化协议？如何选择合适的序列化协议；

Redis渐进式rehash过程？

比如我有个电商平台，做每日订单的异常检测，服务端代码应该写；