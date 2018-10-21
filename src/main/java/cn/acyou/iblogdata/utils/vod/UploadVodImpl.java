/*
 * 文 件 名:  UploadVodImpl
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-20

 */
package cn.acyou.iblogdata.utils.vod;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.common.utils.StringUtils;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.vod.upload.common.Constants;
import com.aliyun.vod.upload.common.Util;
import com.aliyun.vod.upload.dto.UploadTokenDTO;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.oss.OSSClientInternal;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 上午 09:15]
 * @since [小倦鸟/远方模块]
 **/
public class UploadVodImpl extends UploadVideoImpl {

    public UploadFileStreamResponse uploadStream(UploadStreamRequest request) {
        UploadFileStreamResponse uploadFileStreamResponse = new UploadFileStreamResponse();
        CreateUploadVideoRequest createUploadVideoRequest = new CreateUploadVideoRequest();
        createUploadVideoRequest.setTitle(request.getTitle());
        createUploadVideoRequest.setDescription(request.getDescription());
        createUploadVideoRequest.setFileName(request.getFileName());
        createUploadVideoRequest.setCoverURL(request.getCoverURL());
        createUploadVideoRequest.setIP(request.getIP());
        createUploadVideoRequest.setCateId(request.getCateId());
        createUploadVideoRequest.setTags(request.getTags());
        createUploadVideoRequest.setTemplateGroupId(request.getTemplateGroupId());
        createUploadVideoRequest.setStorageLocation(request.getStorageLocation());
        createUploadVideoRequest.setConnectTimeout(Constants.REQUEST_CONNECT_TIMEOUT);
        createUploadVideoRequest.setReadTimeout(Constants.REQUEST_SOCKECT_TIMEOUT);
        CreateUploadVideoResponse createUploadVideoResponse = new CreateUploadVideoResponse();

        try {
            DefaultAcsClient client = this.initVodClient(request.getAccessKeyId(), request.getAccessKeySecret());
            createUploadVideoResponse = (CreateUploadVideoResponse)client.getAcsResponse(createUploadVideoRequest);
        } catch (ClientException var17) {
            uploadFileStreamResponse.setCode(var17.getErrCode());
            uploadFileStreamResponse.setMessage(var17.getErrMsg());
            uploadFileStreamResponse.setRequestId(createUploadVideoResponse.getRequestId());
            return uploadFileStreamResponse;
        }

        String videoId = createUploadVideoResponse.getVideoId();
        request.setRequestId(createUploadVideoResponse.getRequestId());
        if (!StringUtils.isNullOrEmpty(videoId) && !StringUtils.isNullOrEmpty(createUploadVideoResponse.getUploadAuth()) && !StringUtils.isNullOrEmpty(createUploadVideoResponse.getUploadAddress())) {
            String uploadAuth = Util.decodeBase64(createUploadVideoResponse.getUploadAuth()).replaceAll("\n", "").replaceAll("\r", "");
            String uploadAddress = Util.decodeBase64(createUploadVideoResponse.getUploadAddress()).replaceAll("\n", "").replaceAll("\r", "");
            UploadTokenDTO uploadTokenDTO = (UploadTokenDTO)JSON.parseObject(uploadAuth, UploadTokenDTO.class);
            UploadTokenDTO uploadAddressDTO = (UploadTokenDTO)JSON.parseObject(uploadAddress, UploadTokenDTO.class);
            uploadTokenDTO.setBucket(uploadAddressDTO.getBucket());
            uploadTokenDTO.setEndpoint(uploadAddressDTO.getEndpoint());
            uploadTokenDTO.setFileName(uploadAddressDTO.getFileName());
            OSSClientInternal ossClient = null;

            label95: {
                UploadFileStreamResponse var12;
                try {
                    ossClient = this.initOSSClient(uploadTokenDTO.getEndpoint(), uploadTokenDTO.getAccessKeyId(), uploadTokenDTO.getAccessKeySecret(), uploadTokenDTO.getSecurityToken());
                    InputStream inputStream = request.getMultipartFile().getInputStream();
                    PutObjectRequest putObjectRequest = new PutObjectRequest(uploadTokenDTO.getBucket(), uploadTokenDTO.getFileName(), inputStream);
                    putObjectRequest.setHeaders(this.formatUserData(request.getShowWaterMark()));
                    if (!StringUtils.isNullOrEmpty(request.getCallback())) {
                        putObjectRequest.setCallback(this.formatCallback(request.getCallback(), 0L));
                    }

                    ossClient.putObject(putObjectRequest);
                    break label95;
                } catch (FileNotFoundException var18) {
                    uploadFileStreamResponse.setCode(String.format("FileNotFoundException : %s", request.getFileName()));
                    uploadFileStreamResponse.setRequestId(request.getRequestId());
                    var12 = uploadFileStreamResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                    uploadFileStreamResponse.setCode(String.format("IOException : %s", request.getFileName()));
                    uploadFileStreamResponse.setRequestId(request.getRequestId());
                    var12 = uploadFileStreamResponse;
                } finally {
                    if (ossClient != null) {
                        ossClient.shutdown();
                    }
                }

                return var12;
            }

            uploadFileStreamResponse.setCode("Success");
            uploadFileStreamResponse.setMessage("Success");
            uploadFileStreamResponse.setVideoId(videoId);
            uploadFileStreamResponse.setRequestId(request.getRequestId());
            return uploadFileStreamResponse;
        } else {
            uploadFileStreamResponse.setCode("AddVideoInfoFailed");
            uploadFileStreamResponse.setMessage("Adding video info has failed due to some unknown error.");
            uploadFileStreamResponse.setRequestId(createUploadVideoResponse.getRequestId());
            return uploadFileStreamResponse;
        }
    }
}
