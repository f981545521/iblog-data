package cn.acyou.iblogdata.test.other;

import cn.acyou.iblogdata.entity.Student;
import org.junit.Test;

import java.util.Optional;

/**
 * @author youfang
 * @version [1.0.0, 2020/8/19]
 **/
public class TestOptional {
    @Test
    public void test1(){
        String name = "John";
        String name2 = null;
        Optional<String> opt1 = Optional.of(name);
        Optional<String> opt2 = Optional.ofNullable(name);
        // of() 和 ofNullable() 方法创建包含值的 Optional。两个方法的不同之处在于如果你把 null 值作为参数传递进去，of() 方法会抛出 NullPointerException：
        // ofNullable 可以接收null值

        //通过Get 从 Optional 实例中取回实际值对象
        String s1 = Optional.ofNullable(name).get();

        //通过 isPresent 判断是否有值
        Optional.ofNullable(name).isPresent();

        //如果存在值，执行lambda表达式
        Optional.ofNullable(name).ifPresent(s -> System.out.println(s));

        // orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值
        String s2 = Optional.ofNullable(name).orElse("");

        Student student = new Student();
        Optional<Student> xx = Optional.ofNullable(student);
        Integer integer = xx.map(k -> k.getId()).get();
        System.out.println(integer);
        String s = xx.map(k -> k.getName()).orElse("");
        Optional<String> bc = xx.flatMap(k -> Optional.ofNullable(k.getName()));
        String kkk = bc.orElse("");
        System.out.println(kkk);
    }
}
