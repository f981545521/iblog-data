package cn.acyou.iblogdata.upload;

import com.aliyun.oss.OSSClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 下午 01:39]
 **/
public class OOSUploadUtil {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "D:\\tmp\\256.mp4";
        FileInputStream inputStream = new FileInputStream(fileName);
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        //String title = fileName.substring(fileName.lastIndexOf("\\") + 1);
        String title = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET);
        // 上传文件流。
        ossClient.putObject("ib-images", title, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("https://ib-images.oss-cn-beijing.aliyuncs.com/"+ title);
    }
}
