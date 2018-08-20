# Serializable
### 序列化
* 把Java对象转换成字节流

### 反序列化
* 把字节流转换成Java对象

### Why
* 传输
* 存储
* 拷贝

### Serializable.java
* 实现Serializable接口
* 默认使用Java序列化机制
* transient关键字排除field
* 若需要控制序列化流程则实现以下签名的方法
```
    private void writeObject(ObjectOutputStream os) throws IOException {}

    private void readObject(ObjectInputStream is) throws IOException {}
```
[ltd.scau.study.basic.practice.Person](../practice/Person.java)

### Externalizable.java
* 实现Externalizable接口
* 必须包含无参构造方法
* 实现接口方法控制序列化流程

[ltd.scau.study.basic.practice.User](../practice/User.java)
