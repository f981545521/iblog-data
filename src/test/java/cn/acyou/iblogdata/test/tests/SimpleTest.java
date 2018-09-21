package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import com.google.common.collect.Lists;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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


}
