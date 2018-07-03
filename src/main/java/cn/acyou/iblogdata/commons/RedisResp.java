package cn.acyou.iblogdata.commons;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:51]
 **/
public class RedisResp implements Serializable {
    private Integer resultCode;
    private String resultDesc;
    private String key;
    private String data;
    private List<Object> list;
    private Long affectNum;

    public RedisResp() {

    }

    public RedisResp(Integer resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public RedisResp(Integer resultCode, String resultDesc, String key, String data) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.key = key;
        this.data = data;
    }

    public RedisResp(Integer resultCode, String resultDesc, Long affectNum, String key) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.affectNum = affectNum;
        this.key = key;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
