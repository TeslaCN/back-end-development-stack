# 类加载过程

**双亲委派模型**
1. 当一个类加载器接收到加载类的请求，会尝试让父加载器先加载
2. 如果父加载器无法加载，子加载器再尝试加载这个类
3. 如果子加载器仍然加载失败，则抛出java.lang.ClassNotFoundException

```
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
            try {
                if (parent != null) {
                
                    // 交给父类加载
                    c = parent.loadClass(name, false);
                    
                } else {
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```

**自定义java.lang.ClassLoader**
* 重写findClass方法
* 为什么要这么做
  * 字节码需要加密，默认的ClassLoader无法加载
  * 其他需要自定义的行为


1. 加载
   1. 获取类的字节码
   2. 把字节码代表的静态存储结构转换为方法区运行时数据结构
   3. 生成这个类的java.lang.Class实例，作为对方法区中这些数据的访问入口
   
2. 验证
   1. 文件格式验证：版本号，是否0xCAFEBABE开头等
   2. 元数据验证：是否有父类等
   3. 字节码验证：确定程序语义合法、符合逻辑
   4. 符号引用验证：确保解析动作能正确执行

3. 准备
   1. static域分配内存，初始化默认值（非赋值）

4. 解析
  * 解析可能发生于初始化之后
  * 
  
5. 初始化
  * 如果类尚未加载、连接，先进行加载、连接，如果父类也未加载、连接，则从父类开始
  * static从上往下
  * static域声明的值
  * 执行static块