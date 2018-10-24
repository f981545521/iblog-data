package cn.acyou.iblogdata.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-23 下午 04:54]
 **/
@Data
public class StudentLogTestVo implements Serializable {
    private static final long serialVersionUID = -5830138702725396364L;

    private String name;

    private Integer age;

    private MultipartFile headPic;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentLogTestVo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
