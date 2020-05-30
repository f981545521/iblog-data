### StackOverflowError

> 线程栈的溢出，当堆栈溢出的发生是因为一个应用递归太深（应用存在无限递归调用）。

### OutOfMemoryError

> 当java虚拟机不能分配一个对象，它的内存，并没有更多的记忆可以被垃圾收集器可。

无限循环去生成对象，把对象放到List中。

也分好多种：
##### java.lang.OutOfMemoryError: Java heap space

##### java.lang.OutOfMemoryError: GC overhead limit exceeded
【解释】：当GC为释放很小空间占用大量时间时抛出；一般是因为堆太小，导致异常的原因，没有足够的内存。
【解决方案】：
1、查看系统是否有使用大内存的代码或死循环；
2、通过添加JVM配置，来限制使用内存：

##### java.lang.OutOfMemoryError: PermGen space
永久区大小不足
-XX:PermSize 设置持久代(perm gen)初始值
-XX:MaxPermSize 设置持久代最大值

JVM的Perm区主要用于存放Class和Meta信息的,Class在被Loader时就会被放到PermGenspace，这个区域成为年老代，GC在主程序运行期间不会对年老区进行清理，默认是64M大小，
当程序需要加载的对象比较多时，超过64M就会报这部分内存溢出了，需要加大内存分配，一般128m足够。

### JVM内存模型

1. 区域
线程共享：堆、元数据区、直接内存
线程隔离：程序计数器、本地方法栈、Java虚拟机栈

程序计数器（Program Counter Register）：是一块较小的内存空间，他可以看做是当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖计数器完成。
虚拟机栈（Java Virtual Machine Stacks）：描述的是java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧（Stack Frame），用于存储 局部变量、操作数栈、动态链接、方法出口等信息。每一个方法从调用到执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。虚拟机栈也是线程私有的，生命周期于线程相同。
本地方法栈（Method Native Stack）：与虚拟机栈的作用非常相似，区别是虚拟机栈为虚拟机执行java方法服务，而本地方法栈则为虚拟机使用到的Native方法服务。
堆（Java Heap）：此内存区域的唯一作用就是用来存放对象实例，几乎所有的对象实例都在这里分配内存。堆是java虚拟机管理的内存中最大的一块，被所有线程共享。由于堆是垃圾收集器管理的主要区域，所以也被称为GC堆。由于现代收集器基本都采用分代收集算法，所以java堆中还可以细分为新生代，老年代。新生代又细分为Edan区、From Survivor区(S0)、To Survivor区（S1）。
元空间（Metaspace）：存储已被虚拟机加载的类信息。随着JDK8的到来，JVM不再有方法区（PermGen），原方法区存储的信息被分成两部分：1、虚拟机加载的类信息，2、运行时常量池。分别被移动到了元空间和堆中。


https://blog.csdn.net/toward_south/article/details/103024109
https://github.com/TangBean/understanding-the-jvm

testxxxx






















