package cn.acyou.iblogdata.test.other;

import cn.hutool.core.util.ClassLoaderUtil;

import java.util.*;

public class HelloWord {

    public static void main(String[] args) {
        ClassLoader classLoader = HelloWord.class.getClassLoader();
        ClassLoaderUtil.getClassLoader();

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        List<String> vector = new Vector<>();

        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
    }

}
