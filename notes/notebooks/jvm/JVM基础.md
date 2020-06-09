## JVM参数：

#### linux 下调整tomcat的内存设置

修改bin目录下catalina.sh文件

在cygwin=false之上

添加以下语句

JAVA_OPTS="-Xms1024m -Xmx4096m -Xss1024K -XX:PermSize=512m -XX:MaxPermSize=2048m"
example:JAVA_OPTS="-Xms200m -Xmx300m -Xss256K -XX:PermSize=200m -XX:MaxPermSize=300m"
其中-xms为jvm初始化堆的大小，-xmx为jvm堆的最大值

#### window 下调整tomcat的内存设置

修改bin目录下catalina.bat文件@echo off下追加

set JAVA_OPTS=-XX:PermSize=64M -XX:MaxPermSize=128m -Xms512m -Xmx1024m

## 详解：

Xms 是指设定程序启动时占用内存大小。
Xmx 是指设定程序运行期间最大可占用的内存大小。如果程序运行需要占用更多的内存，超出了这个设置值，就会抛出OutOfMemory异常。
Xss 是指设定每个线程的堆栈大小。这个就要依据你的程序，看一个线程大约需要占用多少内存，可能会有多少线程同时运行等。


-Xms 为jvm启动时分配的内存，比如-Xms200m，表示分配200M
-Xmx 为jvm运行过程中分配的最大内存，比如-Xms500m，表示jvm进程最多只能够占用500M内存
-Xss 为jvm启动的每个线程分配的内存大小，默认JDK1.4中是256K，JDK1.5+中是1M
-Xmn2g ：设置年轻代大小为2G。整个堆大小=年轻代大小 + 年老代大小 + 持久代大小 。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。


-XX:NewRatio=4 :设置年轻代（包括Eden和两个Survivor区）与年老代的比值（除去持久代）。设置为4，则年轻代与年老代所占比值为1：4，年轻代占整个堆栈的1/5
-XX:SurvivorRatio=4 ：设置年轻代中Eden区与Survivor区的大小比值。设置为4，则两个Survivor区与一个Eden区的比值为2:4，一个Survivor区占整个年轻代的1/6
-XX:MaxTenuringThreshold=0 ：设置垃圾最大年龄。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代 。对于年老代比较多的应用，可以提高效率。如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间 ，增加在年轻代即被回收的概论。
-XX:PermSize=256m
-XX:MaxPermSize=16m :设置持久代大小为16m。

JDK 1.8 后，方法区（永久代）被彻底移除了	取而代之是元空间，元空间使用的是直接内存。
-XX:MetaspaceSize=N //设置Metaspace的初始（和最小大小）
-XX:MaxMetaspaceSize=N //设置Metaspace的最大大小

为什么要将永久代(PermGen)替换为元空间(MetaSpace)呢?
整个永久代有一个 JVM 本身设置固定大小上线，无法进行调整，而元空间使用的是直接内存，受本机可用内存的限制，并且永远不会得到java.lang.OutOfMemoryError。


-XX:+TraceClassLoading 监控类的加载
一、定义了main的类，启动main方法时该类会被加载
二、创建类的实例，即new对象的时候
三、访问类的静态方法
四、访问类的静态变量
五、反射 Class.forName()

java类在以上五种情况下会被加载。
在jvm生命周期中每个类如果存在，则不会重复加载。
在加载子类的时候会优先加载其父类。
类被加载的时候，其中的静态代码块、静态方法及静态变量也会被加载。
在初始化某个类时，如果这个类的静态代码块、静态方法或静态变量引用到了另一个类，则这个类也会被加载。



2.4.5 Minor Gc和Full GC 有什么不同呢？
大多数情况下，对象在新生代中 eden 区分配。当 eden 区没有足够空间进行分配时，虚拟机将发起一次Minor GC。

新生代GC（Minor GC）:指发生新生代的的垃圾收集动作，Minor GC非常频繁，回收速度一般也比较快。
老年代GC（Major GC/Full GC）:指发生在老年代的GC，出现了Major GC经常会伴随至少一次的Minor GC（并非绝对），Major GC的速度一般会比Minor GC的慢10倍以上。3

