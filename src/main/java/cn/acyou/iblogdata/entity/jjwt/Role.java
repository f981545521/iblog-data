package cn.acyou.iblogdata.entity.jjwt;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-23 上午 09:37]
 **/
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 3336211735447753752L;
    private int id;
    private String description;

    public Role() {
    }

    public Role(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
