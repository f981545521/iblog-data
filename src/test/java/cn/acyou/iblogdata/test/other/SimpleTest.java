package cn.acyou.iblogdata.test.other;

import cn.acyou.iblog.entity.Teacher;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.vo.StudentLogTestVo;
import cn.acyou.iblogdata.vo.StudentVo;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-06 下午 01:49]
 **/
public class SimpleTest {

    @Test
    public void test1(){
        Integer i1 = 1234;
        System.out.println(i1.toString());

        Double d1 = 213D;
        System.out.println(Math.round(d1));
    }

    @Test
    public void test2(){
        String ss = "aaa";
        ss += 1234;
        System.out.println(ss);
    }

    @Test
    public void test3(){
        String audioname = "2132142325435.amr";
        audioname = audioname.substring(0, audioname.lastIndexOf(".")) + ".mp4";
        System.out.println(audioname);
    }

    @Test
    public void test4(){
        System.out.println(100/0);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        try {
            if (n == 2){
                throw new ServiceException();
            }
        }catch (Exception e){
            System.out.println("exception was happend");
            throw new RuntimeException();
        }
        System.out.println("运行结束");
    }

    @Test
    public void randomValue(){
        String[] rooms = new String[]{"123","345","456","3245"};
        for (int i=0;i<100;i++){
            System.out.println(rooms[new Random().nextInt(rooms.length)]);
        }
    }

    @Test
    public void test23s(){
        String[] rooms = new String[]{"123","345","456","3245"};
        Random random = new Random();
        System.out.println(rooms[random.nextInt(rooms.length)]);
        System.out.println(random.nextInt(rooms.length));
    }

    @Test
    public void test3445(){
        int i = 100;
        int j = 102;
        int k = i - j;
        System.out.println(k<0?0:k);
    }

    @Test
    public void test222(){
        double d1 = 3455.5;
        int i1 = new Double(d1).intValue();
        System.out.println(i1);
    }

    @Test
    public void test21(){
        StudentLogTestVo logTestVo = new StudentLogTestVo();
        logTestVo.setName("等等");
        logTestVo.setAge(12);
        Object[] obj = new Object[]{logTestVo};
        System.out.println(obj[0]);
    }

    @Test
    public void test31(){
        int i =1/0;
        System.out.println(i);
    }

    @Test
    public void testw34(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("12", "1230");
        stringMap.put("13", "1230");
        stringMap.put("14", "1230");
        stringMap.put("15", "1230");
        stringMap = new HashMap<>();
        System.out.println(stringMap);
    }

    @Test
    public void test44(){
        StudentVo studentVo = new StudentVo();
        if (studentVo != null && studentVo.getTeacherList() != null && studentVo.getTeacherList().get(1) != null){
            System.out.println(studentVo.getTeacherList().get(1));
        }
        Optional.of(studentVo).map(StudentVo::getTeacherList).ifPresent(System.out::println);

    }
    @Test
    public void test51(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        studentVo.setName("ass");
        System.out.println(studentVo);
        Optional<List<Teacher>> teachers = Optional.of(studentVo).map(StudentVo::getTeacherList);
        System.out.println(studentVo.getTeacherList().size());
    }
    @Test
    public void test52(){
        //调用工厂方法创建Optional实例
        Optional empty = Optional.ofNullable(null);
    }


    @Test
    public void test54(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        studentVo.setName("ass");
        System.out.println(studentVo);
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getAge).orElse(1);
        System.out.println(studentVo);
        System.out.println(studentVo.getTeacherList().size());
    }
    @Test
    public void test55(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        Assert.notNull(studentVo.getName(), "不能为空");
    }
    @Test
    public void test56(){
        StudentVo studentVo = new StudentVo();
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        Optional<String> nameOpt = stuOpt.map(StudentVo::getName);
        String a = nameOpt.orElse("默认你是小明");
        // nameOpt.orElseThrow(() -> new ServiceException("不能为空"));
        System.out.println("好的");
        System.out.println(studentVo);
        Optional.of(studentVo).orElseThrow(() -> new ServiceException("不能为空"));
        //Optional.of(studentVo.getName()).orElseThrow(() -> new ServiceException("不能为空"));
    }

    @Test
    public void test57(){
        StudentVo studentVo = new StudentVo();
        studentVo.setName("小花");
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getName).ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("--------------");
                s = "哈里斯";
                System.out.println(s);
            }
        });
        //stuOpt.map(StudentVo::getTeacherList).orElseThrow(() -> new ServiceException("不能为空"));
        System.out.println(studentVo);
    }
    @Test
    public void test58(){
        StudentVo studentVo = new StudentVo();
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getName);
        System.out.println(studentVo);
    }




}
