package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.commons.BaseReq;
import lombok.Data;


/**
 * @author youfang
 * @version [1.0.0, 2019-01-26 下午 04:18]
 **/
public class TeacherReq extends BaseReq {
    private static final long serialVersionUID = 5726944528516544205L;

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TeacherReq{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

}
