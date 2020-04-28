package cn.acyou.iblogdata.test.jvm.tests;

import cn.acyou.iblogdata.test.jvm.model.Student;
import org.assertj.core.util.Lists;

/**
 * @author youfang
 * @version [1.0.0, 2020/4/27]
 **/
public class MainTest {

    public static void main(String[] args) {
        Student student = new Student("小王", 23, Lists.newArrayList("打球"));
        System.out.println(student);
        student = null;
        System.gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
