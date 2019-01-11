package cn.acyou.iblogdata.test.lambdas;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.RandomUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author youfang
 * @version [1.0.0, 2019-01-11 下午 01:09]
 **/
public class LambdasTest {
    List<Student> studentList = new ArrayList<>();

    @Before
    public void prepareData(){
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
    }

    @Test
    public void test1(){
        List<Integer> ids = studentList.stream().map(Student::getId).collect(Collectors.toList());
        System.out.println(ids);

        List<Integer> ids2 = studentList.stream().filter(x->x.getAge().equals(1)).map(Student::getId).collect(Collectors.toList());
        System.out.println(ids2);

        //转换为Map
        Map<Integer, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getId, x -> x));
        System.out.println(studentMap);

    }

    @Test
    public void test2(){
        // 分组： List里面的对象元素，以某个属性来分组，例如，以age分组，将age相同的放在一起：
        Map<Integer, List<Student>> groupBy = studentList.stream().collect(Collectors.groupingBy(Student::getId));
        //System.out.println(groupBy);

        // 过滤filter: 从集合中过滤出来符合条件的元素 年龄>20的取出
        List<Student> filterList = studentList.stream().filter(x -> x.getAge()>20).collect(Collectors.toList());

        // 求和: 将集合中的数据按照某个属性求和 如：求年龄总和
        int sum = studentList.stream().mapToInt(Student::getAge).sum();
        System.out.println(sum);

        // 查找流中最大 最小值
        Optional<Student> minStudent = studentList.stream().min(Comparator.comparing(Student::getAge));
        Optional<Student> maxStudent = studentList.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
        minStudent.ifPresent(System.out::println);
        maxStudent.ifPresent(System.out::println);

    }
}
