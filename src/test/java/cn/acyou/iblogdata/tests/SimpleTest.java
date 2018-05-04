package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Map;

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
}
