package cn.acyou.iblogdata.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-05-17 上午 11:27]
 **/
@Data
@ApiModel(value="获取用户基本信息", description="获取用户基本信息(UnionID机制)")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -4727455672336529682L;

    //正确时返回的JSON数据包如下：
    @ApiModelProperty(name="subscribe", value="", notes = "用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。")
    private String subscribe;
    @ApiModelProperty(name="openid", value="", notes = "用户的唯一标识")
    private String openid;
    @ApiModelProperty(name="nickname", value="", notes = "用户昵称")
    private String nickname;
    @ApiModelProperty(name="sex", value="", notes = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private String sex;
    @ApiModelProperty(name="province", value="", notes = "用户个人资料填写的省份")
    private String province;
    @ApiModelProperty(name="city", value="", notes = "普通用户个人资料填写的城市")
    private String city;
    @ApiModelProperty(name="country", value="", notes = "国家，如中国为CN")
    private String country;
    @ApiModelProperty(name="language", value="", notes = "用户的语言，简体中文为zh_CN")
    private String language;
    @ApiModelProperty(name="headimgurl", value="", notes = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
    private String headimgurl;
    @ApiModelProperty(name="subscribe_time", value="", notes = "用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间")
    private String subscribe_time;
    @ApiModelProperty(name="privilege", value="", notes = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
    private String privilege;
    @ApiModelProperty(name="remark", value="", notes = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
    private String remark;
    @ApiModelProperty(name="groupid", value="", notes = "用户所在的分组ID（兼容旧的用户分组接口）")
    private String groupid;
    @ApiModelProperty(name="tagid_list", value="", notes = "用户被打上的标签ID列表")
    private String tagid_list;
    @ApiModelProperty(name="subscribe_scene", value="", notes = "返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他")
    private String subscribe_scene;
    @ApiModelProperty(name="unionid", value="", notes = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。")
    private String unionid;
    @ApiModelProperty(name="qr_scene", value="", notes = "二维码扫码场景（开发者自定义）")
    private String qr_scene;
    @ApiModelProperty(name="qr_scene_str", value="", notes = "二维码扫码场景描述（开发者自定义）")
    private String qr_scene_str;


    //错误时微信会返回JSON数据包如下
    @ApiModelProperty(name="errcode", value="", notes = "错误码")
    private String errcode;
    @ApiModelProperty(name="errmsg", value="", notes = "错误消息")
    private String errmsg;
}
