package cn.acyou.iblogdata.test.tests;

import java.lang.reflect.Field;

/**
 * @author youfang
 * @version [1.0.0, 2020/6/4]
 **/
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("cn.acyou.iblogdata.test.entity.CatVo");
        Field[] declaredFields = aClass.getDeclaredFields();
        Package aPackage = aClass.getPackage();
        System.out.println(aPackage.getName());

        Long a = 127L;
        Long b = 127L;

        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
