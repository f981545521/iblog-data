package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.commons.So;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class StudentSo extends So {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;
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