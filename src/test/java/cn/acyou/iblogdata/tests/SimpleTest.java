package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import org.junit.Test;

import java.util.Map;

public class SimpleTest {
    @Test
    public void test21(){
        Student student = new Student();
        student.setId(1223);
        student.setName("张而非");
        student.setAge(null);
        Map<String, Object> map = BeanUtil.convertToMap(student);
        System.out.println(map);
    }
}
