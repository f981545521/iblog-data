package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.commons.So;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-05 下午 05:31]
 **/
public class OssUploadSo extends So {

    private String bucketName;

    private MultipartFile file;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
