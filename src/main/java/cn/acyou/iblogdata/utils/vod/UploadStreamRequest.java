/*
 * 文 件 名:  UploadStreamRequest
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-20

 */
package cn.acyou.iblogdata.utils.vod;

import com.aliyun.vod.upload.req.BaseRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 上午 09:22]
 * @since [小倦鸟/远方模块]
 **/
public class UploadStreamRequest extends BaseRequest {
    //文件对象
    private MultipartFile multipartFile;
    public UploadStreamRequest(String accessKeyId, String accessKeySecret, String title, String fileName) {
        super(accessKeyId, accessKeySecret, title, fileName);
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
