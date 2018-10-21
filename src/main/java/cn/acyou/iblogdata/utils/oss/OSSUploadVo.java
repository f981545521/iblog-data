/*
 * 文 件 名:  OSSUploadVo.java
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0> 
 * 创 建 人:  令东来
 * 创建时间:  2018年5月15日
 
 */
package cn.acyou.iblogdata.utils.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 文件上传实体
 * @author  令东来
 * @version  [1.0.0, 2018年5月15日]
 * @since  [小胖鸟/远方模块]
 */
public class OSSUploadVo implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 3417707543386134604L;
    
    /**
     * endpoint以杭州[http://oss-cn-hangzhou.aliyuncs.com]为例，其它region请按实际情况填写
     */
    private String endpoint;
    
    /**
     * 秘钥ID
     */
    private String accessKeyId;
    
    /**
     * 秘钥key
     */
    private String accessKeySecret;
    
    /**
     * 存储对象空间名称
     */
    private String bucketName;
    
    /**
     * 对象空间中的文件名
     */
    private String key;
    
    /**
     * 客户端实例
     */
    private OSS client;
    
    /**
     * 阿里云生成文件上传ID
     */
    private String uploadId;
    
    /**
     * 本地上传文件路径
     */
    private String localFilePath;
    
    /**
     * 每片文件大小
     */
    private long partSize = 5 * 1024 * 1024L;
    
    /**
     * 头部信息
     */
    private ObjectMetadata objectMetadata;
    
    /**
     * 视频地址
     */
    private String onlineUrl;
    
    private File uploadFile;
    
    private InputStream inputStream;
    
    /**
     * @return 返回 inputStream
     */
    public InputStream getInputStream()
    {
        return inputStream;
    }
    
    /**
     * @param 对inputStream进行赋值 */
    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
    
    /**
     * @return 返回 uploadFile
     */
    public File getUploadFile()
    {
        return uploadFile;
    }
    
    /**
     * @param 对uploadFile进行赋值 */
    public void setUploadFile(File uploadFile)
    {
        this.uploadFile = uploadFile;
    }
    
    /**
     * @return 返回 onlineUrl
     */
    public String getOnlineUrl()
    {
        return onlineUrl;
    }
    
    /**
     * @param 对onlineUrl进行赋值 */
    public void setOnlineUrl(String onlineUrl)
    {
        this.onlineUrl = onlineUrl;
    }
    
    /**
     * @return 返回 objectMetadata
     */
    public ObjectMetadata getObjectMetadata()
    {
        return objectMetadata;
    }
    
    /**
     * @param 对objectMetadata进行赋值 */
    public void setObjectMetadata(ObjectMetadata objectMetadata)
    {
        this.objectMetadata = objectMetadata;
    }
    
    /**
     * @return 返回 partSize
     */
    public long getPartSize()
    {
        return partSize;
    }
    
    /**
     * @param 对partSize进行赋值 */
    public void setPartSize(long partSize)
    {
        this.partSize = partSize;
    }
    
    /**
     * @return 返回 localFilePath
     */
    public String getLocalFilePath()
    {
        return localFilePath;
    }
    
    /**
     * @param 对localFilePath进行赋值 */
    public void setLocalFilePath(String localFilePath)
    {
        this.localFilePath = localFilePath;
    }
    
    /**
     * @return 返回 uploadId
     */
    public String getUploadId()
    {
        return uploadId;
    }
    
    /**
     * @param 对uploadId进行赋值 */
    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }
    
    /**
     * @return 返回 client
     */
    public OSS getClient()
    {
        return client;
    }
    
    /**
     * @param 对client进行赋值 */
    public void setClient(OSS client)
    {
        this.client = client;
    }
    
    /**
     * @return 返回 endpoint
     */
    public String getEndpoint()
    {
        return endpoint;
    }
    
    /**
     * @param 对endpoint进行赋值 */
    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }
    
    /**
     * @return 返回 accessKeyId
     */
    public String getAccessKeyId()
    {
        return accessKeyId;
    }
    
    /**
     * @param 对accessKeyId进行赋值 */
    public void setAccessKeyId(String accessKeyId)
    {
        this.accessKeyId = accessKeyId;
    }
    
    /**
     * @return 返回 accessKeySecret
     */
    public String getAccessKeySecret()
    {
        return accessKeySecret;
    }
    
    /**
     * @param 对accessKeySecret进行赋值 */
    public void setAccessKeySecret(String accessKeySecret)
    {
        this.accessKeySecret = accessKeySecret;
    }
    
    /**
     * @return 返回 bucketName
     */
    public String getBucketName()
    {
        return bucketName;
    }
    
    /**
     * @param 对bucketName进行赋值 */
    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey()
    {
        return key;
    }
    
    /**
     * @param 对key进行赋值 */
    public void setKey(String key)
    {
        this.key = key;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("【OSSUploadVo】:{");
        buffer.append("endpoint:" + endpoint + ",");
        buffer.append("accessKeyId:" + accessKeyId + ",");
        buffer.append("accessKeySecret:" + accessKeySecret + ",");
        buffer.append("bucketName:" + bucketName + ",");
        buffer.append("key:" + key);
        buffer.append("}");
        return buffer.toString();
    }
}
