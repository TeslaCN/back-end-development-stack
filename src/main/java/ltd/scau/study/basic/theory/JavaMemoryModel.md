# JVM运行时内存区域划分

* VM Stack (Java VM Stack)
  * Stack Frame
     1. 局部变量表
     2. 操作数栈
     3. 常量池引用
     4. 返回的地址
     5. 附加信息
     
* Heap
  * 存储对象
  
* Method Stack
  * 方法区中，存储了每个类的信息（包括类的名称、方法信息、字段信息）、静态变量、常量以及编译器编译后的代码等
  
* Native Method Stack
  * HotSpot VM 与 Java VM Stack 合并
  * 执行本地方法
  
* Program Counter Register
  * 指向执行指令