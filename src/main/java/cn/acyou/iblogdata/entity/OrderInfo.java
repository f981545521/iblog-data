package cn.acyou.iblogdata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 下午 04:38]
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 2243781552939925322L;

    private Long orderId;

    private Long memberId;

    private Integer orderType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String productsId;

    private String productsName;

}
