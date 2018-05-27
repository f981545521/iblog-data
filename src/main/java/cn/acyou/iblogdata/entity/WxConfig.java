/*
 * 文 件 名:  JsapiTicket
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-05-23

 */
package cn.acyou.iblogdata.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-05-23 上午 09:56]
 * @since [小倦鸟/远方模块]
 **/
@Data
@ApiModel(value="微信使用JSAPI先配置config", description="通过config接口注入权限验证配置。")
public class WxConfig implements Serializable {
    private static final long serialVersionUID = -1803860272436488738L;

    @ApiModelProperty(name="appId", value="", notes = "公众号的唯一标识")
    private String appId;

    @ApiModelProperty(name="timestamp", value="", notes = "生成签名的时间戳")
    private String timestamp;

    @ApiModelProperty(name="nonceStr", value="", notes = "生成签名的随机串")
    private String nonceStr;

    @ApiModelProperty(name="signature", value="", notes = "签名")
    private String signature;

    @ApiModelProperty(name="jsApiList", value="", notes = "需要使用的JS接口列表")
    private List<String> jsApiList;
}
