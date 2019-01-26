package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.commons.BaseReq;
import lombok.Data;


/**
 * @author youfang
 * @version [1.0.0, 2019-01-26 下午 04:18]
 **/
@Data
public class TeacherReq extends BaseReq {
    private static final long serialVersionUID = 5726944528516544205L;

    private String name;

    private Integer age;

}
