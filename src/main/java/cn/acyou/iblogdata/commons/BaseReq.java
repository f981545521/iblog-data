package cn.acyou.iblogdata.commons;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2019-01-26 下午 04:16]
 **/
public class BaseReq implements Serializable {
    private static final long serialVersionUID = 710922411420253669L;

    private String logId;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseReq{");
        sb.append("logId='").append(logId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
