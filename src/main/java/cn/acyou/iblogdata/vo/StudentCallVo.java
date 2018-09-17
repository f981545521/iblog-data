package cn.acyou.iblogdata.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-9-13 下午 10:45]
 **/
@Data
public class StudentCallVo implements Serializable {
    private static final long serialVersionUID = -7937518394909234526L;

    private Integer age;
    private Integer total;
}
