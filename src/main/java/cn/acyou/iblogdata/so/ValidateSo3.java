package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.annotation.BaseValid;
import cn.acyou.iblogdata.annotation.EnhanceValid;

import java.io.Serializable;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class ValidateSo3 implements Serializable {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;

    @EnhanceValid({
        @BaseValid(notNull = true, message = "姓名不能为空"),
        @BaseValid(maxLength = 10, message = "姓名过长"),
        @BaseValid(minLength = 2, message = "姓名过短"),
        @BaseValid(range = {"张三", "李四"}, message = "姓名不在范围内")
    })
    private String name;


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
}
