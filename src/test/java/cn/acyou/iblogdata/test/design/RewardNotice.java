package cn.acyou.iblogdata.test.design;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-17 下午 03:52]
 **/
@Data
public class RewardNotice implements Serializable {
    private static final long serialVersionUID = -1364904319972595690L;
    private String targetId;//目标ID
    private Integer templateType = 2;//模板类型
    private String targetNickname;
    private String detailUrl;//详情连接
}
