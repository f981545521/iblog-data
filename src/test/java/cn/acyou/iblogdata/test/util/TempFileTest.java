package cn.acyou.iblogdata.test.util;

import java.io.File;
import java.io.IOException;

/**
 * 临时文件测试
 * @author youfang
 * @version [1.0.0, 2018-09-03 下午 05:58]
 **/
public class TempFileTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        createTempFile();
        Thread.sleep(10000);
        System.out.println("运行结束");
    }
    public static void createTempFile() throws IOException {
        File source = File.createTempFile("temp", "source.amr");
        File target = File.createTempFile("temp", "target.mp3");
        System.out.println(source.getAbsolutePath());
        source.deleteOnExit();//将在程序运行结束后删除
        target.delete();//立即删除
    }
}
