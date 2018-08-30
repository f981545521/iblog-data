package cn.acyou.iblogdata.test.other;

import cn.acyou.iblogdata.exception.ServiceException;
import org.junit.Test;

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
}
