package cn.acyou.iblogdata.so;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-15 上午 11:34]
 **/
@Data
public class ArrayParamReq implements Serializable {
    private static final long serialVersionUID = 6883316490360983966L;
    private List<String> ids;

    private String name;
}
