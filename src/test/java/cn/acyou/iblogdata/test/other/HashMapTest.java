package cn.acyou.iblogdata.test.other;

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


    @Test
    public void testHashMap(){

        //Map<Integer, String> stringMap = new HashMap<>(Math.round(50 / 0.75f));
        Map<Integer, String> stringMap = new HashMap<>();

        for (int i = 0; i < 50; i++) {
            Integer k = i;
            String v = "OK" + i;
            stringMap.put(k, v);
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




}
