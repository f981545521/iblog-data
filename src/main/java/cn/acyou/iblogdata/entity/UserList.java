package cn.acyou.iblogdata.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @version [1.0.0, 2018-05-17 上午 11:06]
 **/
@Data
@ApiModel(value="获取用户列表", description="获取用户列表")
public class UserList implements Serializable {

    private static final long serialVersionUID = 7320922346343556631L;
    //正确时返回的JSON数据包如下：
    @ApiModelProperty(name="total", value="", notes = "关注该公众账号的总用户数")
    private String total;
    @ApiModelProperty(name="count", value="", notes = "拉取的OPENID个数，最大值为10000")
    private String count;
    @ApiModelProperty(name="data", value="", notes = "列表数据，OPENID的列表")
    private Map<String, List<String>> data;
    @ApiModelProperty(name="next_openid", value="", notes = "拉取列表的最后一个用户的OPENID")
    private String openid;

    //错误时微信会返回JSON数据包如下
    @ApiModelProperty(name="errcode", value="", notes = "错误码")
    private String errcode;
    @ApiModelProperty(name="errmsg", value="", notes = "错误消息")
    private String errmsg;

}
