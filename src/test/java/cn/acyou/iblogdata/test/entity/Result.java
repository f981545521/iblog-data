package cn.acyou.iblogdata.test.entity;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2019-07-24 下午 09:09]
 * @since [司法公证]
 **/
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private boolean success = true;
    private T data;

    public Result() {
    }

    public Result(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static Result success(){
        return new Result(200, "请求成功", true, null);
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
