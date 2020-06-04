### JDK动态代理为什么需要接口？
主要原因是代理类已经继承了Proxy类，Java不支持多继承所以导致动态代理需要使用接口，接口支持多继承。
添加CGLIB库aspectjweaver-xxx.jar

5、JDK动态代理和CGLIB字节码生成的区别？
1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类。

2）CGLIB是针对类实现代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类。

### ConcurrentHashMap
采用了数组+Segment+分段锁的方式实现
1.Segment(分段锁)
ConcurrentHashMap中的分段锁称为Segment，它即类似于HashMap的结构，即内部拥有一个Entry数组，
数组中的每个元素又是一个链表,同时又是一个ReentrantLock（Segment继承了ReentrantLock）。

2.内部结构

ConcurrentHashMap使用分段锁技术，将数据分成一段一段的存储，然后给每一段数据配一把锁，
当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问，能够实现真正的并发访问。
，ConcurrentHashMap定位一个元素的过程需要进行两次Hash操作。

第一次Hash定位到Segment，第二次Hash定位到元素所在的链表的头部。