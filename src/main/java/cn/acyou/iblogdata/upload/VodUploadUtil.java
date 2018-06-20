/*
 * 文 件 名:  VodUploadUtil
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-19

 */
package cn.acyou.iblogdata.upload;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 阿里云视频点播上传
 * @author youfang
 * @version [1.0.0, 2018-06-19 下午 03:02]
 * @since [小倦鸟/远方模块]
 **/
public class VodUploadUtil {

    public static void main(String[] args) throws Exception{
/*        String fileName = "D:\\tmp\\256.mp4";
        //String videoId = uploadToVod(fileName, null, null);
        String videoId = "662221f1dfba4f9dadbdb6d7ec32d79e";
        System.out.println(videoId);
        GetPlayInfoResponse response = getPlayInfo(videoId);
        System.out.println(response.getVideoBase().getCoverURL());
        System.out.println(response.getPlayInfoList().get(0).getPlayURL());*/


    }

    /**
     * 本地文件上传接口
     * @param fileName 文件全路径
     * @param cateId 视频分类ID(可选)
     * @param templateGroupId 模板组ID(可选)
     * @return videoId
     */
    public static String uploadToVod(String fileName, Long cateId, String templateGroupId) {
        /* 文件标题 */
        String title = fileName.substring(fileName.lastIndexOf("\\") + 1);
        UploadVideoRequest request = new UploadVideoRequest(UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(1 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        //request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setIsShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        if (cateId != null){
            request.setCateId(cateId);
        }
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        if (StringUtils.isEmpty(templateGroupId)){
            request.setTemplateGroupId(templateGroupId);
        }
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        UploadVideoImpl uploader = new UploadVideoImpl();
        /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("videoId = " + response.getVideoId() + "\n");  //请求视频点播服务的请求ID
        return response.getVideoId();
    }

    /**
     * 获取视频详情
     * @param videoId 视频Id
     * @return response
     */
    public static GetPlayInfoResponse getPlayInfo(String videoId){
        DefaultAcsClient client = initVodClient();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client, videoId);
            GetPlayInfoResponse.VideoBase base = response.getVideoBase();
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            return response;
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        } finally {
            return response;
        }
    }

    /**
     * 初始化客户端
     * @return Client
     */
    private static DefaultAcsClient initVodClient() {
        //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET);
        return new DefaultAcsClient(profile);
    }

    /**
     * 获取播放地址函数
     */
    private static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String videoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    public static CreateUploadVideoResponse createUploadVideo(String fileName) {
        DefaultAcsClient client = initVodClient();
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        CreateUploadVideoResponse response = null;
        try {
            /*必选，视频源文件名称（必须带后缀, 支持 ".3gp", ".asf", ".avi", ".dat", ".dv", ".flv", ".f4v", ".gif", ".m2t", ".m3u8", ".m4v", ".mj2", ".mjpeg", ".mkv", ".mov", ".mp4", ".mpe", ".mpg", ".mpeg", ".mts", ".ogg", ".qt", ".rm", ".rmvb", ".swf", ".ts", ".vob", ".wmv", ".webm"".aac", ".ac3", ".acm", ".amr", ".ape", ".caf", ".flac", ".m4a", ".mp3", ".ra", ".wav", ".wma"）*/
            request.setFileName(fileName);
            //必选，视频标题
            request.setTitle(fileName);
/*            //可选，分类ID
            request.setCateId(0);
            //可选，视频标签，多个用逗号分隔
            request.setTags("标签1,标签2");
            //可选，视频描述
            request.setDescription("视频描述");*/
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            System.out.println("CreateUploadVideoRequest Server Exception:");
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            System.out.println("CreateUploadVideoRequest Client Exception:");
            e.printStackTrace();
            return null;
        }
        System.out.println("RequestId:"+response.getRequestId());
        System.out.println("UploadAuth:"+response.getUploadAuth());
        System.out.println("UploadAddress:"+response.getUploadAddress());
        return response;
    }


    /**
     * 文件流上传接口
     */
    public static String uploadFileStream(MultipartFile multipartFile) {
        if (multipartFile.getSize() > UploadConstant.ACCESS_MAX_SIZE){
            throw new RuntimeException("上传文件大小超过限制！");
        }
        String title = multipartFile.getOriginalFilename();
        String fileName = multipartFile.getOriginalFilename();
        UploadStreamRequest request = new UploadStreamRequest(UploadConstant.ACCESS_KEY_ID, UploadConstant.ACCESS_KEY_SECRET, title, fileName);
        request.setMultipartFile(multipartFile);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        UploadVodImpl uploader = new UploadVodImpl();
        UploadFileStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }


}
