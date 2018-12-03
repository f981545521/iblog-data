package cn.acyou.iblogdata.vo;

import java.io.Serializable;

/**
 * https://blog.csdn.net/qq_27093465/article/details/53861020
 *
 * 【强制】POJO 类中布尔类型的变量，都不要加 is 前缀，否则部分框架解析会引起序列化错误。
 * 反例：定义为基本数据类型 Boolean isDeleted；的属性，它的方法也是 isDeleted()，RPC
 * 阿里巴巴 Java 开发手册
 * 2/36
 * 框架在反向解析的时候，“误以为”对应的属性名称是 deleted，导致属性获取不到，进而抛 出异常。
 * @author youfang
 * @version [1.0.0, 2018-12-03 上午 11:24]
 **/
public class AuditorVo implements Serializable {
    private static final long serialVersionUID = -4537224380889796170L;

    private Integer isDeleted;

    private boolean isWeiXin;

    private boolean h5;

    private byte po;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isWeiXin() {
        return isWeiXin;
    }

    public void setWeiXin(boolean weiXin) {
        isWeiXin = weiXin;
    }

    public boolean isH5() {
        return h5;
    }

    public void setH5(boolean h5) {
        this.h5 = h5;
    }
}
