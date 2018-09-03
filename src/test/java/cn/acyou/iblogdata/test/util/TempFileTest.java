package cn.acyou.iblogdata.test.util;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 临时文件测试
 * @author youfang
 * @version [1.0.0, 2018-09-03 下午 05:58]
 **/
public class TempFileTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        //createTempFile();
        //Thread.sleep(10000);
        //System.out.println("运行结束");
        File file = new File("source.amr");
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testWrite() throws IOException {
        File file = new File("source.amr");
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objOut= new ObjectOutputStream(outputStream);
        objOut.writeObject("123");
        objOut.flush();
        objOut.close();
        outputStream.close();
    }

    @Test
    public void convertMultipartFile() throws IOException {
        File file = new File("F:\\iotest\\123.mp4");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile targetFile = new MockMultipartFile(file.getName(), inputStream);
        //                                                 文件名          原始文件名      文件类型                               文件流
        //MultipartFile targetFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.TEXT_PLAIN.getMimeType(), inputStream);
        System.out.println(targetFile.getSize());

    }

    @Test
    public void testContentType(){
        System.out.println(ContentType.TEXT_PLAIN.getMimeType());
    }


    public static void createTempFile() throws IOException {
        File source = File.createTempFile("temp", "source.amr");
        File target = File.createTempFile("temp", "target.mp3");
        System.out.println(source.getAbsolutePath());
        source.deleteOnExit();//将在程序运行结束后删除
        target.delete();//立即删除
    }
}
