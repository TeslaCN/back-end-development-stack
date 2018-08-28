#多线程HashMap问题

##貌似适用于JDK1.7及之前？

JDK1.7及之前，HashMap在resize()的时候，会将链表逆置，由此可能产生环导致死循环

JDK1.8在扩容会保留原有链表的顺序，不会产生环，解决了死循环问题  
但HashMap是非线程安全的，在多线程环境不应该使用HashMap  

线程安全的[java.util.concurrent.ConcurrentHashMap](ConcurrentHashMap.md)

