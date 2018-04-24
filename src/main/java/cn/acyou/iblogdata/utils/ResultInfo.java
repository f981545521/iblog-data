package cn.acyou.iblogdata.utils;

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

    public static ResultInfo generateSuccess(Object data){
        return new ResultInfo(data);
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
