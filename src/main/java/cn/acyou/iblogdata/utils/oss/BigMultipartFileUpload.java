/*
 * 文 件 名:  OSSMultipartUploadUtil.java
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0> 
 * 创 建 人:  令东来
 * 创建时间:  2018年5月15日
 */
package cn.acyou.iblogdata.utils.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  阿里云-大文件分片上传【不支持断点方式】
 * @author  观棋老祖
 * @version  [1.0.0, 2018年5月15日]
 * @since  [小胖鸟/远方模块]
 */
public class BigMultipartFileUpload
{
    private final static Logger logger = Logger.getLogger(BigBreakPointFileUpload.class);
    
    //分片上传线程数
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    
    //上传成功的分片收集
    private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
    
    /**
     * 初始化分片上传
     * @param vo
     * @param client
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static void initOSSClient(OSSUploadVo vo)
    {
        logger.debug("使用访问OSS的帐户构造客户端实例....");
        // 使用访问OSS的帐户构造客户端实例
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        OSS client = new OSSClientBuilder().build(vo.getEndpoint(), vo.getAccessKeyId(), vo.getAccessKeySecret(), conf);
        //生成上传文件实例ID
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(vo.getBucketName(), vo.getKey());
        request.setObjectMetadata(vo.getObjectMetadata());
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        vo.setUploadId(result.getUploadId());
        vo.setClient(client);
    }
    
    /**
     * 分片上传-本地文件
     * @param vo
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static String uploadFileByOSS(OSSUploadVo vo)
        throws IOException
    {
        OSS client = null;
        try
        {
            client = vo.getClient();
            String uploadId = vo.getUploadId();
            String bucketName = vo.getBucketName();
            String key = vo.getKey();
            logger.info("生成上传文件ID：" + uploadId);
            final long partSize = vo.getPartSize(); // 5MB
            logger.debug("【开始】计算要分成多少部分,每片大小:" + partSize);
            final File sampleFile = new File(vo.getLocalFilePath());
            long fileLength = sampleFile.length();
            int partCount = (int)(fileLength / partSize);
            if (fileLength % partSize != 0)
            {
                partCount++;
            }
            if (partCount > 10000)
            {
                logger.error("零件总数不应超过10000");
                throw new RuntimeException("Total parts count should not exceed 10000");
            }
            else
            {
                logger.info("【结束】总共分片数量:" + partCount);
            }
            logger.info("【开始】上传文件到OSS对象存储空间中");
            for (int i = 0; i < partCount; i++)
            {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(
                    new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId, key, bucketName, client));
            }
            logger.info("【上传中】等待所有分片上传完成....");
            executorService.shutdown();
            while (!executorService.isTerminated())
            {
                try
                {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            logger.info("【验证中】验证所有分片上传完成....");
            if (partETags.size() != partCount)
            {
                logger.error("【验证失败】上传多个部件失败，因为一些部件尚未完成");
                throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
            }
            else
            {
                logger.info("【验证中】所有分片已经成功上传");
            }
            /**
             * 列出文件所有的分块清单并打印到日志中，该方法仅仅作为输出使用
             */
            logger.info("【验证中】查看最近上传的所有部件");
            //            listAllParts(uploadId, key, bucketName, client);
            logger.info("【合成】合成上传部分");
            completeMultipartUpload(uploadId, key, bucketName, client);
            // 设置URL过期时间为10年 3600l* 1000*24*365*10
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 20);
            URL url = client.generatePresignedUrl(bucketName, key, expiration);
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
            partETags.clear();
            if (client != null)
            {
                client.shutdown();
                if (vo.getClient() != null)
                {
                    vo.getClient().shutdown();
                }
            }
        }
    }
    
    private static class PartUploader implements Runnable
    {
        
        private File localFile;
        
        private long startPos;
        
        private long partSize;
        
        private int partNumber;
        
        private String uploadId;
        
        private String key;
        
        private String bucketName;
        
        private OSS client;
        
        public PartUploader(File localFile, long startPos, long partSize, int partNumber, String uploadId, String key,
            String bucketName, OSS client)
        {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
            this.key = key;
            this.bucketName = bucketName;
            this.client = client;
        }
        
        @Override
        public void run()
        {
            InputStream instream = null;
            try
            {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);
                
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);
                
                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                logger.debug("分片:" + this.partNumber + " done");
                synchronized (partETags)
                {
                    partETags.add(uploadPartResult.getPartETag());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (instream != null)
                {
                    try
                    {
                        instream.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    //    private static void listAllParts(String uploadId, String key, String bucketName, OSS client)
    //    {
    //        logger.debug("【验证中】罗列出所有分片");
    //        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
    //        PartListing partListing = client.listParts(listPartsRequest);
    //        
    //        int partCount = partListing.getParts().size();
    //        for (int i = 0; i < partCount; i++)
    //        {
    //            PartSummary partSummary = partListing.getParts().get(i);
    //            logger.debug("【验证中】分片:" + partSummary.getPartNumber() + ", ETag=" + partSummary.getETag());
    //        }
    //    }
    
    private static void completeMultipartUpload(String uploadId, String key, String bucketName, OSS client)
    {
        logger.debug("【验证中】按升序排列分片编号");
        Collections.sort(partETags, new Comparator<PartETag>()
        {
            @Override
            public int compare(PartETag p1, PartETag p2)
            {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
            new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }
    
}
