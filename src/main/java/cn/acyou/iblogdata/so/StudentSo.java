package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.annotation.valid.BaseValid;
import cn.acyou.iblogdata.annotation.valid.EnhanceValid;
import cn.acyou.iblogdata.commons.So;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class StudentSo extends So {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;

    @EnhanceValid({
            @BaseValid(notNull = true, message = "学生姓名不能为空")
    })
    private String name;

    private Boolean useNow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUseNow() {
        return useNow;
    }

    public void setUseNow(Boolean useNow) {
        this.useNow = useNow;
    }
}
