package cn.acyou.iblogdata.upload;

import com.aliyun.vod.upload.req.BaseRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-20 上午 09:22]
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
