/*
 * 文 件 名:  BigBreakPointFileUpload.java
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0> 
 * 创 建 人:  令东来
 * 创建时间:  2018年5月16日
 
 */
package cn.acyou.iblogdata.utils.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.UploadFileRequest;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Date;

/**
 * 大文件-断点上传
 * @author  令东来
 * @version  [1.0.0, 2018年5月16日]
 * @since  [小胖鸟/远方模块]
 */
public class BigBreakPointFileUpload
{
    private final static Logger logger = Logger.getLogger(BigBreakPointFileUpload.class);
    
    private static OSS client = null;
    
    /**
     * 推荐-文件断点上传
     * @param vo
     * @return
     * @throws Throwable
     * @see [类、类#方法、类#成员]
     */
    public static String uploadFile(OSSUploadVo vo)
        throws Throwable
    {
        logger.debug("初始化实例..");
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClientBuilder().build(vo.getEndpoint(), vo.getAccessKeyId(), vo.getAccessKeySecret(), conf);
        try
        {
            // 设置断点续传请求
            logger.debug("设置断点续传请求...");
            UploadFileRequest uploadFileRequest = new UploadFileRequest(vo.getBucketName(), vo.getKey());
            //设置头部信息
            logger.debug("设置头部信息...");
            uploadFileRequest.setObjectMetadata(vo.getObjectMetadata());
            // 指定上传的本地文件
            uploadFileRequest.setUploadFile(vo.getLocalFilePath());
            // 指定上传并发线程数
            uploadFileRequest.setTaskNum(5);
            // 指定上传的分片大小
            uploadFileRequest.setPartSize(vo.getPartSize());
            // 开启断点续传
            uploadFileRequest.setEnableCheckpoint(true);
            // 断点续传上传
            logger.debug("上传开始....");
            client.uploadFile(uploadFileRequest);
            logger.debug("上传成功....");
            // 设置URL过期时间为10年 3600l* 1000*24*365*10
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 20);
            URL url = client.generatePresignedUrl(vo.getBucketName(), vo.getKey(), expiration);
            if (url != null)
            {
                String[] arrSplit = url.toString().split("[?]");
                return arrSplit[0];
            }
            return null;
        }
        catch (OSSException oe)
        {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
            return null;
        }
        catch (ClientException ce)
        {
            logger.error("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
            return null;
        }
        finally
        {
            if (client != null)
            {
                client.shutdown();
            }
        }
    }
}
