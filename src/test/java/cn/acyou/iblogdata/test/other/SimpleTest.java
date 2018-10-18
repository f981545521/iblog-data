package cn.acyou.iblogdata.test.other;

import cn.acyou.iblogdata.exception.ServiceException;
import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

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
}
