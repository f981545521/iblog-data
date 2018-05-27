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

/**
 * @author youfang
 * @version [1.0.0, 2018-05-23 上午 09:56]
 * @since [小倦鸟/远方模块]
 **/
@Data
@ApiModel(value="调用微信JS接口的临时票据", description="jsapi_ticket是公众号用于调用微信JS接口的临时票据。")
public class JsapiTicket implements Serializable {
    private static final long serialVersionUID = -1803860272436488738L;

    @ApiModelProperty(name="errcode", value="", notes = "错误码")
    private String errcode;

    @ApiModelProperty(name="errmsg", value="", notes = "错误消息")
    private String errmsg;

    @ApiModelProperty(name="ticket", value="", notes = "jsapi_ticket是公众号用于调用微信JS接口的临时票据。")
    private String ticket;

    @ApiModelProperty(name="expires_in", value="", notes = "有效期7200秒")
    private String expires_in;
}
