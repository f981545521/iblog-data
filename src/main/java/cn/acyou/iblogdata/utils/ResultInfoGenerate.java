package cn.acyou.iblogdata.utils;

/**
 * Static类生成响应信息
 * @author youfang
 * @date 2018-04-25 下午 05:36
 **/
public class ResultInfoGenerate {

    public static ResultInfo generateSuccess(Object data){
        return new ResultInfo(data);
    }
}
