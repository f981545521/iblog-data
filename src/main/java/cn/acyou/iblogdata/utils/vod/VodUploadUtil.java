/*
 * 文 件 名:  VodUploadUtil
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-19

 */
package cn.acyou.iblogdata.utils.vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
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

    private static final Logger log = LoggerFactory.getLogger(VodUploadUtil.class);
    //Vod客户端
    private static DefaultAcsClient client = initVodClient();

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
        UploadVideoRequest request = new UploadVideoRequest(VodUploadConstant.ACCESS_KEY_ID, VodUploadConstant.ACCESS_KEY_SECRET, title, fileName);
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
        return response.getVideoId();
    }

    /**
     *
     * 获取视频详情, 获取播放地址
     * @param videoId 视频Id
     * @return response
     */
    public static GetPlayInfoResponse getPlayInfo(String videoId){
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            request.setVideoId(videoId);
            response =  client.getAcsResponse(request);
            return response;
        } catch (Exception e) {
            log.debug("ErrorMessage = " + e.getLocalizedMessage());
            return response;
        }
    }

    /**
     * 从响应信息中获取出资源路径
     * @param response playInfo
     * @param format 格式类型（mp4/m3u8）
     * @return URL
     */
    public static String getPlayResourceUrl(GetPlayInfoResponse response, String format){
        String playURL = "";
        if (CollectionUtils.isEmpty(response.getPlayInfoList())){
            return playURL;
        }
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList){
            if (format.equalsIgnoreCase(playInfo.getFormat())){
                playURL = playInfo.getPlayURL();
            }
        }
        return playURL;
    }

    /**
     * 初始化客户端
     * @param accessKeyId accessKeyId
     * @param accessKeySecret accessKeySecret
     * @return Client
     */
    private static DefaultAcsClient initVodClient() {
        //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, VodUploadConstant.ACCESS_KEY_ID, VodUploadConstant.ACCESS_KEY_SECRET);
        return new DefaultAcsClient(profile);
    }

    public static CreateUploadVideoResponse createUploadVideo(String fileName) {
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
            log.debug("CreateUploadVideoRequest Server Exception:");
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            log.debug("CreateUploadVideoRequest Client Exception:");
            e.printStackTrace();
            return null;
        }
        return response;
    }


    /**
     * 文件流上传接口
     * @param multipartFile 文件对象
     * @param cateId 视频分类ID(可选)
     * @param templateGroupId 模板组ID(可选)
     */
    public static String uploadFileStream(MultipartFile multipartFile, String title, Long cateId, String templateGroupId) {
        if (multipartFile.getSize() > VodUploadConstant.ACCESS_MAX_SIZE){
            throw new RuntimeException("文件大小超过最大限制");
        }
        if (StringUtils.isEmpty(title)){
            title = multipartFile.getOriginalFilename();
        }
        String fileName = multipartFile.getOriginalFilename();
        UploadStreamRequest request = new UploadStreamRequest(VodUploadConstant.ACCESS_KEY_ID, VodUploadConstant.ACCESS_KEY_SECRET, title, fileName);
        request.setMultipartFile(multipartFile);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
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
        if (!StringUtils.isEmpty(templateGroupId)){
            request.setTemplateGroupId(templateGroupId);
        }
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        UploadVodImpl uploader = new UploadVodImpl();
        UploadFileStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            log.debug("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            log.debug("ErrorCode=" + response.getCode() + "\n" + "ErrorMessage=" + response.getMessage());
        }
        return response.getVideoId();
    }

    /**
     * 获取源文件信息函数
     * @param videoId videoId
     */
    public static GetMezzanineInfoResponse getMezzanineInfo(String videoId) throws Exception {
        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
        request.setVideoId(videoId);
        //源片下载地址过期时间
        request.setAuthTimeout(3600L);
        return client.getAcsResponse(request);
    }

    /**
     * 获取视频列表函数
     */
    public static GetVideoListResponse getVideoList() throws Exception {
        GetVideoListRequest request = new GetVideoListRequest();
        // 视频状态，默认获取所有状态的视频，多个用逗号分隔
        // request.setStatus("Uploading,Normal,Transcoding");
        request.setPageNo(1L);
        request.setPageSize(20L);
        return client.getAcsResponse(request);
    }



/*    public static void main(String[] args) throws InterruptedException {
        List<String> videoIds = Lists.newArrayList();
        videoIds.add("972ea857d9e84ed29e42b9818a922cdc");
        videoIds.add("a7da73d3aa1345bcabd8da050604e634");
        videoIds.add("9088f7516a4e4a7ea207de7d0d5e89b3");
        videoIds.add("ef6a896c26244ca5b3731a3926da64b5");
        videoIds.add("662221f1dfba4f9dadbdb6d7ec32d79e");
        videoIds.add("ee0ac2ab89f64b2fb59105f0241d28b7");
        videoIds.add("f2d73c36b0d44fddbfb19a9092238cdf");
        videoIds.add("ff960911eecd4a5ba76976eaa8834e97");
        videoIds.add("e5c720113850473e80e4a8c7c8a22208");
        videoIds.add("83a8ded19c934029b4166eb4b37172f1");
        Random random = new Random();

        //1.1、 创建CountDownLatch 对象， 设定需要计数的子线程数目
        final CountDownLatch latch=new CountDownLatch(50);
        System.out.println("主线程开始执行。。。");
        Long l1 = System.currentTimeMillis();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i =0 ; i< 10; i ++){
                    int index = random.nextInt(videoIds.size());
                    String videoId = videoIds.get(index);
                    GetPlayInfoResponse response = VodUploadUtil.getPlayInfo(videoId);
                    System.out.println(Thread.currentThread().getName() + "," + response.getVideoBase().getVideoId());
                    //1.2、子线程执行完毕，计数减1
                    latch.countDown();
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable1);
        Thread thread3 = new Thread(runnable1);
        Thread thread4 = new Thread(runnable1);
        Thread thread5 = new Thread(runnable1);
        //1.3、 当前线程挂起等待
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        latch.await();
        Long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        System.out.println("主线程执行完毕....");
    }*/


    public static void main(String[] args) throws Exception {
        String videoId = "972ea857d9e84ed29e42b9818a922cdc";
        GetPlayInfoResponse response = VodUploadUtil.getPlayInfo(videoId);
        System.out.println(response);
    }


}
