package cn.acyou.iblogdata.test.tests;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-20 下午 09:02]
 **/
public class RandomAccessFileTest {

    private static final String url = "E:\\temp\\test.txt";

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(new File(url), "rw");
        byte[] bytes = "12345".getBytes(StandardCharsets.UTF_8);
        raf.write(bytes);
        byte[] bytes2 = "123456".getBytes(StandardCharsets.UTF_8);
        raf.write(bytes2);
        byte[] bytes3 = "1234567".getBytes(StandardCharsets.UTF_8);
        raf.write(bytes3);
        byte[] bytes4 = "12345678".getBytes(StandardCharsets.UTF_8);
        raf.write(bytes4);
        raf.close();
    }

    @Test
    public void testListIndex(){
        List<String> stringList = new ArrayList<>();
        stringList.add("123");
        stringList.add("456");
        stringList.add("789");
        stringList.add("435\r\n");
        stringList.add(2, "AAA");
        System.out.println(stringList);
    }
    @Test
    public void testListIndex2(){
        String ENTITY_FILE_PATH = "\\src\\main\\java\\cn\\acyou\\iblogdata\\test";
        int i = ENTITY_FILE_PATH.indexOf("\\src\\main\\java\\");
        String s = ENTITY_FILE_PATH.substring(i + 15);
        System.out.println(s.replace("\\","."));

    }
}
