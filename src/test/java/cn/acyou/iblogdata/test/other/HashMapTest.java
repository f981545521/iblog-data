package cn.acyou.iblogdata.test.other;

import cn.hutool.core.util.ZipUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author youfang
 * @version [1.0.0, 2020/4/27]
 **/
public class HashMapTest {


    /**
     * http://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/
     */
    @Test
    public void testHashMapOne(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        /**
         * 1. 对key的hashCode()做hash，然后再计算index;
         * 2. 如果没碰撞直接放到bucket里；
         * 3. 如果碰撞了，以链表的形式存在buckets后；
         * 4. 如果碰撞导致链表过长(大于等于8)，就把链表转换成红黑树；
         * 5. 如果节点已经存在就替换old value(保证key的唯一性)
         * 6. 如果bucket满了(超过load factor*current capacity)，就要resize。
         */

        /**
         * Hash方法的实现：高16bit不变，低16bit和高16bit做了一个异或。其中代码注释是这样写的：
         */

        /**
         * 以Entry[]数组实现的哈希桶数组，用Key的哈希值取模桶数组的大小可得到数组下标。
         *
         * 插入元素时，如果两条Key落在同一个桶（比如哈希值1和17取模16后都属于第一个哈希桶），我们称之为哈希冲突。
         *
         * JDK的做法是链表法，Entry用一个next属性实现多个Entry以单向链表存放。查找哈希值为17的key时，先定位到哈希桶，然后链表遍历桶里所有元素，逐个比较其Hash值然后key值。
         *
         * 在JDK8里，新增默认为8的阈值，当一个桶里的Entry超过閥值，就不以单向链表而以红黑树来存放以加快Key的查找速度。
         *
         * 当然，最好还是桶里只有一个元素，不用去比较。所以默认当Entry数量达到桶数量的75%时，哈希冲突已比较严重，就会成倍扩容桶数组，并重新分配所有原来的Entry。扩容成本不低，所以也最好有个预估值。
         *
         * 取模用与操作（hash & （arrayLength-1））会比较快，所以数组的大小永远是2的N次方， 你随便给一个初始值比如17会转为32。默认第一次放入元素时的初始值是16。
         *
         * iterator（）时顺着哈希桶数组来遍历，看起来是个乱序
         *
         * 参考资料
         */

        //Hash表是，Node   bucket  hash桶
        //转为链表是发生hash冲突的时候，以链表存在hash Node里，链表长度TREEIFY_阈值(8)的时候，将链表转为红黑树(将Node转为TreeNode)
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    @Test
    public void testHashMap(){

        //Map<Integer, String> stringMap = new HashMap<>(Math.round(50 / 0.75f));
        Map<Integer, String> stringMap = new HashMap<>();

        for (int i = 0; i < 80; i++) {
            Integer k = i;
            String v = "OK" + i;
            stringMap.put(k, v);
            if (i == 50){
                stringMap.put(k, "XXXXXXXXXXX");
            }
        }
        stringMap.put(10, "1000");
        System.out.println(6 ^ 3);
    }

    @Test
    public void testHashMap2(){
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        //初始大小为16，最大为2的30次方，负载因子0.75，达到值时，发生扩容，扩2倍的大小。
        //实现是通过hash表的数组结构。Node<K,V>[]
        HashMap<Integer, String> hashMap = new HashMap<>();

        //具有可预测迭代顺序的，头和尾的链表结构
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "222");
        linkedHashMap.put(33, "222");
        linkedHashMap.put(4, "222");
        linkedHashMap.put(6, "222");
        linkedHashMap.get(1);

        Hashtable<Integer, String> hashtable = new Hashtable<>();
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
    }

    @Test
    public void testCollection(){
        //初始大小为10，每次add的时候回检查是否足够，不够的时候回增加容量，每次增加上一次的一半。Arrays.copyOf
        //实现是通过Object[]
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String v = "OK" + i;
            arrayList.add(v);
        }
        System.out.println(arrayList);
        ArrayList<Object> objects = Lists.newArrayListWithExpectedSize(50);
        ArrayList<Object> objects2 = Lists.newArrayListWithCapacity(50);


        List<String> linkedList = new LinkedList<>();
        List<String> vector = new Vector<>();

        //是通过HashMap的实现
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();

    }

    @Test
    public void test123(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();


    }


    @Test
    public void testTreeMap(){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("1", "111");
        treeMap.put("2", "222");
        treeMap.put("3", "333");
        treeMap.put("1", "111");

        NavigableSet<String> strings = treeMap.descendingKeySet();
        System.out.println(strings);
        System.out.println(treeMap);

        //弹出并删除第一个节点
        treeMap.pollFirstEntry();
        //返回的键值映射与最关键的大于或等于给定的键，或 null如果没有这样的关键。
        Map.Entry<String, String> entry = treeMap.ceilingEntry("1");
        System.out.println(entry);

        System.out.println(treeMap);


    }




}
