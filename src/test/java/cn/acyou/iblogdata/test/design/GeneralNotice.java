package cn.acyou.iblogdata.test.design;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-17 下午 03:49]
 **/
public class GeneralNotice implements Serializable {

    private static final long serialVersionUID = 6702645812455051866L;
    private String targetId;//目标ID
    private Integer templateType = 1;//模板类型
    private String detailUrl;//详情连接

    public String getTargetId() {
        return targetId;
    }

    public GeneralNotice setTargetId(String targetId) {
        this.targetId = targetId.trim();
        return this;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public GeneralNotice setTemplateType(Integer templateType) {
        this.templateType = templateType;
        return this;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public GeneralNotice setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl.trim();
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GeneralNotice{");
        sb.append("targetId='").append(targetId).append('\'');
        sb.append(", templateType=").append(templateType);
        sb.append(", detailUrl='").append(detailUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
