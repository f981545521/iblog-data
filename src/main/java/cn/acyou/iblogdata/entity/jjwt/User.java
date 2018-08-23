package cn.acyou.iblogdata.entity.jjwt;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-23 上午 09:37]
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1124599746941877436L;
    private String id;
    private Role role;
}
