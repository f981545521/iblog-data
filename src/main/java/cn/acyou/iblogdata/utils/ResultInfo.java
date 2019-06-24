package cn.acyou.iblogdata.utils;

import cn.acyou.iblogdata.constant.AppConstant;

/**
 * @author youfang
 * @date 2017-12-05 17:22
 **/
public class ResultInfo {
    private int code;
    private String message;
    private Object data;

    public ResultInfo() {
        this.code = AppConstant.SUCCESS;
        this.message = AppConstant.MESSAGE;
    }

    public ResultInfo(Object data) {
        this.code = AppConstant.SUCCESS;
        this.message = AppConstant.MESSAGE;
        this.data = data;
    }


    public ResultInfo(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ResultInfo success(){
        return new ResultInfo();
    }
    public static ResultInfo success(Object data){
        return new ResultInfo(data);
    }
    public static ResultInfo error(String message){
        return new ResultInfo(500, null, message);
    }
    public static ResultInfo error(String message, Object data){
        return new ResultInfo(500, data, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultInfo{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
