package cn.acyou.iblogdata.test.entity;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-14 下午 03:18]
 **/
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    public String name;

    public String no;

    @Transient
    public String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
