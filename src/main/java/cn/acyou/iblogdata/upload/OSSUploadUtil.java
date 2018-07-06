package cn.acyou.iblogdata.upload;

import cn.acyou.iblogdata.vo.OSSUploadVo;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 下午 01:39]
 **/
public class OSSUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(OSSUploadUtil.class);

    //OSSClient实例
    private static OSSClient ossClient = initOssClient();

    /**
     * 阿里云 文件流上传
     * @param ossUploadVo file
     * @return URL
     */
    public static String uploadOssByStream(OSSUploadVo ossUploadVo){
        InputStream inputStream = null;
        try {
            inputStream = ossUploadVo.getFile().getInputStream();
            String fileName = ossUploadVo.getFile().getOriginalFilename();
            String title;
            if (StringUtils.isNotEmpty(ossUploadVo.getFileName())){
                title = ossUploadVo.getFileName() + fileName.substring(fileName.lastIndexOf("."));
            }else {
                title = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            }
            // 上传文件流。
            logger.warn("开始上传....");
            ossClient.putObject(ossUploadVo.getBucketName(), title, inputStream);
            logger.warn("上传结束。");
            // 关闭OSSClient。关闭之后就要重新实例化一个
            //ossClient.shutdown();
            return getUploadUrl(ossUploadVo.getBucketName(), title);
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
            return getUploadUrl(UploadConstant.BUCKETNAME.IB_OTHERS, title);
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
    private static String getUploadUrl(String bucketName, String title){
        String start = UploadConstant.OSS_ENDPOINT.substring(0, UploadConstant.OSS_ENDPOINT.lastIndexOf("/") + 1);
        String end = UploadConstant.OSS_ENDPOINT.substring(UploadConstant.OSS_ENDPOINT.lastIndexOf("/") + 1);
        return start + bucketName + "." + end + "/" + title;
    }

    /**
     * 创建OSSClient实例。
     * @return OSSClient实例
     */
    private static OSSClient initOssClient(){
        logger.warn("初始化OSSclient...");
        return new OSSClient(UploadConstant.OSS_ENDPOINT, UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET);
    }

    /**
     * 列举存储空间。
     */
    public static List<String> listBuckets(){
        List<Bucket> bucketsbuckets = ossClient.listBuckets();
        List<String> buckNameList = Lists.transform(bucketsbuckets, new Function<Bucket, String>() {
            @Override
            public String apply(Bucket input) {
                return input.getName();
            }
        });
        return buckNameList;
        //下面这句比较高级，需要有时间研究一下
        //return bucketsbuckets.stream().map(Bucket::getName).collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception{
/*        String str = "http://fs.w.kugou.com/201806211850/f215f44079296457af01356d640ec274/G123/M0A/1A/08/G4cBAFsp97WAMWxiAEURHVT3DRg816.mp3";
        String str2 = "http://fs.w.kugou.com/201806211905/0b18de10b99d2b6c52e90879140b2f97/G123/M0A/1A/08/G4cBAFsp97WAMWxiAEURHVT3DRg816.mp3";
        System.out.println(OSSUploadUtil.uploadOssByURLStream(str));
        System.out.println(OSSUploadUtil.uploadOssByURLStream(str2));*/
        //OSSUploadUtil.uploadOssByURLStream(str2);
        List<String> buckets = OSSUploadUtil.listBuckets();
        for (String s: buckets){
            System.out.println(s);
        }
    }



}
