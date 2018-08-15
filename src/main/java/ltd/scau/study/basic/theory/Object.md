#java.lang.Object

**Method**
* Object
* registerNatives
* getClass
* hashCode
* equals
* clone
* toString
* notify()
* notifyAll()
* wait() equals to wait(0)

```
synchronize(obj) {
    while (condition doesn't hold) {
        obj.wait();
    }
}
```

* wait(long timeout)
* wait(long timeout, int nanos)
* finalize()


