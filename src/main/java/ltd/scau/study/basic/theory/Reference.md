# Reference 引用

* 强引用
  * Object o = new Object()
  * 回收 o = null
  * 不被引用后，GC自动回收
  * 只要有引用就不会被回收

* 软引用
  * java.lang.ref.SoftReference
  * 内存不足时回收

* 弱引用
  * java.lang.ref.WeakReference
  * 一旦被垃圾收集器发现，则回收

* 虚引用
  * 任何时候可能被回收
