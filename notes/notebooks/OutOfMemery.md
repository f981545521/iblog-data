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

