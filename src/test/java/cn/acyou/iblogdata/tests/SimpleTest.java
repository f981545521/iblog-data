package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;

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

}
