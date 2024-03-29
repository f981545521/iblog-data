package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.test.entity.DossierEvidenceDetail;
import cn.acyou.iblogdata.utils.BeanUtil;
import cn.acyou.iblogdata.utils.ParamUtil;
import cn.acyou.iblogdata.utils.QueueUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.TimeoutUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import static cn.acyou.iblogdata.utils.ParamUtil.replaceSpecialStr;

public class SimpleTest {
    @Test
    public void test21(){
        Student student = new Student();
        Student student2 = new Student();
        student.setId(1223);
        student.setName("张而非");
        student.setAge(null);
        Map<String, Object> map = BeanUtil.convertToMap(student);
        BeanUtils.copyProperties(student, student2);
        System.out.println(student2);
    }

    @Test
    public void test212() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Student student = new Student();
        student.setId(1223);
        student.setName("张而非");
        student.setAge(null);
        Map<String, Object> map = BeanUtil.convertBean(student);
        System.out.println(map);
    }
    @Test
    public void test2121() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Student student = new Student();
        student.setId(1223);
        student.setName("张而非");
        student.setAge(23);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", "王力宏");
        objectMap.put("id", 1000);
        objectMap.put("age", 33);
        Student stu = BeanUtil.convertMapToBean(Student.class, objectMap);

/*        String jsonStr = JSON.toJSONString(objectMap);
        System.out.println(jsonStr);
        Student stu = JSON.parseObject(jsonStr, Student.class);*/
        System.out.println(stu);

    }

    /**
     * 转义<></> 防止XSS
     */
    @Test
    public void test22(){
        String ss = "<script>alert(1)</script>";
        String ss2 = "safsfafsfa";
        String aa = ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        System.out.println(aa);
        System.out.println(ss.replaceAll("<", "&lt;"));
        System.out.println(ss.replaceAll(">", "&gt;"));
    }



    @Test
    public void etst2(){
        List<Map<String, String>> list = Lists.newArrayList();
        Map<String, String> map = new HashMap<String, String>();
        map.put("232","23");
        map.put("233","23");
        list.add(map);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("232","fsf");
        map2.put("235","23");
        list.add(map2);
        for (int i = 0; i < list.size(); i++){
            Map<String, String> item = list.get(i);
            if ("1".equals(item.get("code"))) {
                list.remove(item);
            }
        }
        Set<Map.Entry<String, String>> set =  map.entrySet();
        Iterator<Map.Entry<String, String>> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, String> item = iterator2.next();
            item.getKey();
        }

        Iterator<Map<String, String>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Map<String, String> item = iterator.next();
            if ("fsf".equals(item.get("232"))) {
                iterator.remove();
            }
        }
        System.out.println(list);

    }

    @Test
    public void test12(){
        you23:{
            System.out.println("helloword");
            for (int i = 0;i<30;i++){
                if (i == 20){
                    break you23;
                }
                System.out.println(i);
            }
        }
    }


    @Test
    public void test3(){
        String fileName = "D:\\tmp\\256.mp4";
        System.out.println(fileName.substring(fileName.lastIndexOf("\\") + 1));
        String fileName2 = "/user/56.mp4";
        System.out.println(fileName2.lastIndexOf("\\"));
    }

    @Test
    public void test4(){
        List<String> videoIds = Lists.newArrayList();
        videoIds.add("972ea857d9e84ed29e42b9818a922cdc");
        videoIds.add("a7da73d3aa1345bcabd8da050604e634");
        videoIds.add("9088f7516a4e4a7ea207de7d0d5e89b3");
        videoIds.add("ef6a896c26244ca5b3731a3926da64b5");
        videoIds.add("662221f1dfba4f9dadbdb6d7ec32d79e");
        videoIds.add("ee0ac2ab89f64b2fb59105f0241d28b7");
        videoIds.add("f2d73c36b0d44fddbfb19a9092238cdf");
        videoIds.add("ff960911eecd4a5ba76976eaa8834e97");
        videoIds.add("e5c720113850473e80e4a8c7c8a22208");
        videoIds.add("83a8ded19c934029b4166eb4b37172f1");
        Random random = new Random();
        int index = random.nextInt(videoIds.size());
        System.out.println(videoIds.get(index));
    }


    @Test
    public void test23(){
        String duration = "3.32425";
        int index = duration.indexOf(".");
        if (index != -1){
            System.out.println(duration.substring(0, index));
        }
    }

    @Test
    public void test24(){
        StringBuilder sb = new StringBuilder();
        sb.append(123);
        sb.append("12345");
        System.out.println(sb);

    }

    @Test
    public void test25(){
        Map<String, Object> memberIdMap = Maps.newHashMap("memberId","100032");
        System.out.println(memberIdMap);
    }

    @Test
    public void test26(){
        String url = "<p>亲爱的{{targetNickName}}：</p><p>欢迎您加入小倦鸟！</p>";
        String res = url.replace("{{targetNickName}}", "小三");
        System.out.println(res);
    }

    @Test
    public void test27(){
        Long memberId = 1032L;
        System.out.println(String.format("%05d", memberId));

        //String m2 = "10";
        //System.out.println(String.format("%05d", m2));

    }

    @Test
    public void test28(){
        String str = "<p><br/></p>";
        System.out.println(replaceSpecialStr(str));
    }

    @Test
    public void test29(){
        String str = "<p>\r\n<br/></p>";
        System.out.println(ParamUtil.removeDangerStr(str));
    }

    @Test
    public void test30() throws Exception{
        String s = "1. 阿萨德\r\n 2. 水电费";
        String result = URLEncoder.encode(s, "utf-8");
        System.out.println(result);
        String res = "1.%20%E9%98%BF%E8%90%A8%E5%BE%B7%0D%0A%202.%20%E6%B0%B4%E7%94%B5%E8%B4%B9";
        System.out.println(URLDecoder.decode(res, "utf-8"));

        //var b = "1. 阿萨德\r\n 2. 水电费";
        //encodeURI(b)
        //"1.%20%E9%98%BF%E8%90%A8%E5%BE%B7%0D%0A%202.%20%E6%B0%B4%E7%94%B5%E8%B4%B9"
        //var c = "1.%20%E9%98%BF%E8%90%A8%E5%BE%B7%0D%0A%202.%20%E6%B0%B4%E7%94%B5%E8%B4%B9";
        //decodeURI(c)
        //"1. 阿萨德
        // 2. 水电费"
    }

    @Test
    public void test31(){
        String word = "Pneumonoultramicroscopicsilicovolcanoconiosis";
        System.out.println(word.length());
    }

    @Test
    public void test32(){
        //int valueTen = 305419896;
        int valueTen = 1412569788;
        String strHex = Integer.toHexString(valueTen);
        System.out.println(strHex);//12345678
    }

    @Test
    public void test33(){
        Long duration = TimeUnit.DAYS.toDays(1);
        System.out.println(duration);
        System.out.println(TimeUnit.HOURS.convert(duration, TimeUnit.DAYS));

        long seconds = TimeoutUtils.toSeconds(1L, TimeUnit.DAYS);
        System.out.println(seconds);
    }

    @Test
    public void test34(){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sa", "fdsff");
        System.out.println(stringMap);
    }

    @Test
    public void test35(){
        boolean b = StringUtils.equalsIgnoreCase("xx","xx");
        System.out.println(b);
    }

    @Test
    public void test36() {
        //还有这种操作？？？
        Student student = new Student();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            student.setName("小明" + i);
            studentList.add(student);
        }
        System.out.println(studentList);
    }
    @Test
    public void test37() {
        Student student = new Student();
        List<Student> studentList = new ArrayList<>();
        student.setName("小明Y");
        studentList.add(student);
        student.setName("小明U");
        studentList.add(student);
        Student student2 = new Student();
        student2.setName("小明U");
        for (Student stu: studentList){
            System.out.println(stu == student2);
            System.out.println(stu.equals(student2));
        }
        System.out.println(studentList);
    }


    @Test
    public void test39(){
        System.out.println(365*24*60*60);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test40(){
        String name = "1234";
        System.out.println(name.getBytes().length);
        Byte v = Byte.MAX_VALUE;
        String s = String.valueOf(v);
        Byte k = Byte.valueOf(s);


        System.out.println(new Date().getTime());
        System.out.println(new Date(System.currentTimeMillis()));
    }

    @Test
    public void test123(){
        String s = "/mkt/bargain/BA201901241841030947713";
        System.out.println(s.indexOf("/mkt/bargain/"));
    }

    @Test
    public void test333(){
        StringBuilder sb = new StringBuilder();
        String alisa = "ed.";
        Field[] fields = ReflectUtil.getFields(DossierEvidenceDetail.class);
        for (Field e: fields){
            if ("serialVersionUID".equals(e.getName())){
                continue;
            }
            sb.append(alisa).append(e.getName()).append(", ");
        }
        System.out.println(sb);
    }

    @Test
    public void test3334(){
        String s = "{group=公证事项:公证事项名称 spliter=顿号}";
        int i = s.indexOf("spliter=");
    }

    @Test
    public void test334543(){
        Deque<String> stringDeque = new ConcurrentLinkedDeque<>();
        stringDeque.add("111");
        stringDeque.add("112");
        stringDeque.add("113");
        stringDeque.add("114");
        stringDeque.add("115");
        stringDeque.add("116");
        stringDeque.add("117");

        List<String> strings = QueueUtil.dequePop(stringDeque, 5);

        System.out.println(strings);
        System.out.println(stringDeque);
    }
    @Test
    public void test3345433(){
        Deque<String> stringDeque = new ConcurrentLinkedDeque<>();

    }


}
