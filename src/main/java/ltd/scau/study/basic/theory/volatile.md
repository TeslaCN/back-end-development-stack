# volatile

硬件层面
1. 总线加锁
2. 缓存一致性协议
   * CPU写缓存，如果是共享缓存，会通知其他CPU将该缓存置为无效
   * CPU读写无效缓存的时候，会重新从主存读取数据
   
   
并发编程：
1. 原子性

2. 可见性
* 线程1取变量到缓存并修改，没有立刻写回主存
* 线程2从主存读取变量，仍然为线程1修改前的数据
* 即线程1的修改对于线程2不可见

3. 有序性
```
//线程1:
context = loadContext();   //语句1
inited = true;             //语句2
 
//线程2:
while(!inited ){
  sleep()
}
doSomethingwithconfig(context);
```



