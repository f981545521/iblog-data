package cn.acyou.iblogdata.test.other;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author youfang
 * @version [1.0.0, 2020/4/27]
 **/
public class HashCodeConflictTest {

    @Test
    public void testHash(){
        int hash = Objects.hash("李易峰");
        System.out.println((16 - 1) & hash);
        /*
         * n 是hash的长度
         * hash 是hash值
         * first = tab[(n - 1) & hash]) != null
         *
         * 得到在哪个hash表中，直接去取
         */


    }

    @Test
    public void testHashMapOne() {
        HashMap<Student, Integer> map = new HashMap<>();
        map.put(new Student("李易峰", "19"), 1);
        map.put(new Student("张学友", "19"), 1);
        map.put(new Student("张三丰", "19"), 1);
        map.put(new Student("王富贵", "19"), 1);
        map.put(new Student("刘德华", "19"), 1);
        /* 重写了hashCode方法，只有name一样的时候，在一个桶中。但是equals方法比较的是name与年龄，此时将发生hash冲突 */
        map.put(new Student("李易峰", "50"), 1);



        for (Map.Entry<Student, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    class Student {

        private String name;
        private String age;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name) &&
                    Objects.equals(age, student.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }


}
