package cn.acyou.iblogdata.test.lambdas;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.RandomUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author youfang
 * @version [1.0.0, 2019-01-11 下午 01:09]
 **/
public class LambdasTest {

    @Test
    public void test1(){
        List<Student> studentList = new ArrayList<>();
        for (int i =1;i<10;i++){
            Student student = new Student();
            student.setId(i);
            student.setName("小炮" + i);
            Integer age = 1;
            if (i%2==0){
                age = NumberUtils.toInt(RandomUtil.createRandom(true, 2));
            }
            student.setAge(age);
            System.out.println(student);
            studentList.add(student);
        }
        List<Integer> ids = studentList.stream().map(Student::getId).collect(Collectors.toList());
        System.out.println(ids);

        List<Integer> ids2 = studentList.stream().filter(x->x.getAge().equals(1)).map(Student::getId).collect(Collectors.toList());
        System.out.println(ids2);

        //转换为Map
        Map<Integer, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getId, x -> x));
        System.out.println(studentMap);


    }
}
