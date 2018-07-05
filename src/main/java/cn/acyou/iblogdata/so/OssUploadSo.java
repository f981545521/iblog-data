package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.commons.So;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-05 下午 05:31]
 **/
public class OssUploadSo extends So {

    private String bucketName;

    private String fileName;

    private MultipartFile myfile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public MultipartFile getMyfile() {
        return myfile;
    }

    public void setMyfile(MultipartFile myfile) {
        this.myfile = myfile;
    }
}
