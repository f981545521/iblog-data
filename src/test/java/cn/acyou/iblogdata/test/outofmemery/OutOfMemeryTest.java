package cn.acyou.iblogdata.test.outofmemery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/nisen6477/article/details/100018801
 * @author youfang
 * @version [1.0.0, 2020/5/29]
 **/
public class OutOfMemeryTest {
    public static void main(String[] args) {
        //testOutOfMemoryError();

        OutOfMemeryTest outOfMemeryTest = new OutOfMemeryTest();
        outOfMemeryTest.stackOverFlowErrorByVariable3();
    }

    public static void testOutOfMemoryError(){
        System.out.println("start...");
        List<File> files = new ArrayList<>();
        while (true){
            File file = new     File("F:\\server\\flowable-6.4.1.zip");
            files.add(file);
        }
        //Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
    }

    private int stackLength = 1;

    /**
     * 栈上的stackOverFlowError
     * -Xss128k 设置栈大小
     */
    public void stackOverFlowErrorByVariable() {
        stackLength++;
        stackOverFlowErrorByVariable();
        //Exception in thread "main" java.lang.StackOverflowError
    }

    public void stackOverFlowErrorByVariable2() {
        stackOverFlowErrorByVariable2();
        //方法递归的时候死循环
        //Exception in thread "main" java.lang.StackOverflowError
    }

    public void stackOverFlowErrorByVariable3() {
        System.out.println("start...");
        List<File> filesRefference = new ArrayList<>();
        File orginalFile = new File("F:\\server\\flowable-6.4.1.zip");
        while (true){
            File tempFile = orginalFile;
            filesRefference.add(tempFile);
        }
    }

}
