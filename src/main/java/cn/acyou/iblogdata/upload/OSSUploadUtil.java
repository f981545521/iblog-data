package cn.acyou.iblogdata.upload;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 下午 01:39]
 **/
public class OSSUploadUtil {

    //OSSClient实例
    private static OSSClient ossClient = initOssClient();

    /**
     * 阿里云 文件流上传
     * @param multipartFile file
     * @return URL
     */
    public static String uploadOssByStream(MultipartFile multipartFile){
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            String title = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            // 上传文件流。
            ossClient.putObject(UploadConstant.BUCKETNAME.IB_IMAGES, title, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return getUploadUrl(UploadConstant.BUCKETNAME.IB_IMAGES);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 阿里云 URL上传
     * @param inputURL url
     * @return URL
     */
    public static String uploadOssByURLStream(String inputURL){
        URL url = null;
        try {
            url = new URL(inputURL);
            InputStream inputStream = url.openStream();
            String title = UUID.randomUUID().toString() + inputURL.substring(inputURL.lastIndexOf("."));
            // 上传文件流。
            ossClient.putObject(UploadConstant.BUCKETNAME.IB_OTHERS, title, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return getUploadUrl(UploadConstant.BUCKETNAME.IB_OTHERS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 拼接成返回路径
     * @param bucketName bucketName
     * @return 返回路径
     */
    private static String getUploadUrl(String bucketName){
        String start = UploadConstant.OSS_ENDPOINT.substring(0, UploadConstant.OSS_ENDPOINT.lastIndexOf("/") + 1);
        String end = UploadConstant.OSS_ENDPOINT.substring(UploadConstant.OSS_ENDPOINT.lastIndexOf("/") + 1);
        return start + bucketName + "." + end;
    }

    /**
     * 创建OSSClient实例。
     * @return OSSClient实例
     */
    private static OSSClient initOssClient(){
        System.out.println("初始化OSSclient");
        return new OSSClient(UploadConstant.OSS_ENDPOINT, UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET);
    }

    public static void main(String[] args) throws Exception{
        String str = "http://fs.w.kugou.com/201806211850/f215f44079296457af01356d640ec274/G123/M0A/1A/08/G4cBAFsp97WAMWxiAEURHVT3DRg816.mp3";
        String str2 = "http://fs.w.kugou.com/201806211905/0b18de10b99d2b6c52e90879140b2f97/G123/M0A/1A/08/G4cBAFsp97WAMWxiAEURHVT3DRg816.mp3";
        System.out.println(OSSUploadUtil.uploadOssByURLStream(str));
        System.out.println(OSSUploadUtil.uploadOssByURLStream(str2));
        //OSSUploadUtil.uploadOssByURLStream(str2);
    }



}
