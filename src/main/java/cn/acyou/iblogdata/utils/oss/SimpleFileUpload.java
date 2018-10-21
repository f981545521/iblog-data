/*
 * 文 件 名:  SimpleFileUpload.java
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0> 
 * 创 建 人:  令东来
 * 创建时间:  2018年5月16日
 
 */
package cn.acyou.iblogdata.utils.oss;

import com.aliyun.oss.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 简单文件上传
 * @author  令东来
 * @version  [1.0.0, 2018年5月16日]
 * @since  [小胖鸟/远方模块]
 */
public class SimpleFileUpload
{
    private final static Logger logger = Logger.getLogger(BigBreakPointFileUpload.class);
    
    private static OSS client = null;
    
    /**
     * 上传网络流
     * @param vo
     * @return
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws Throwable
     * @see [类、类#方法、类#成员]
     */
    public static void uploadFileByOnline(OSSUploadVo vo)
        throws MalformedURLException, IOException
    {
        logger.debug("初始化实例..");
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClientBuilder().build(vo.getEndpoint(), vo.getAccessKeyId(), vo.getAccessKeySecret(), conf);
        try
        {
            // 上传网络流
            logger.debug("上传网络流");
            InputStream inputStream = new URL(vo.getOnlineUrl()).openStream();
            client.putObject(vo.getBucketName(), vo.getKey(), inputStream, vo.getObjectMetadata());
        }
        catch (OSSException oe)
        {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        }
        catch (ClientException ce)
        {
            logger.error("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
        }
        finally
        {
            if (client != null)
            {
                client.shutdown();
            }
        }
    }
    
    /**
     * 上传网络流
     * @param vo
     * @return
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws Throwable
     * @see [类、类#方法、类#成员]
     */
    public static void uploadFileByInputStream(OSSUploadVo vo)
        throws MalformedURLException, IOException
    {
        logger.debug("初始化实例..");
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClientBuilder().build(vo.getEndpoint(), vo.getAccessKeyId(), vo.getAccessKeySecret(), conf);
        try
        {
            // 上传网络流
            logger.debug("上传文件流");
            client.putObject(vo.getBucketName(), vo.getKey(), vo.getInputStream(), vo.getObjectMetadata());
        }
        catch (OSSException oe)
        {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        }
        catch (ClientException ce)
        {
            logger.error("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
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
