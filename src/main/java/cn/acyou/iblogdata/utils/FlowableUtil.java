package cn.acyou.iblogdata.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author youfang
 * @version [1.0.0, 2020/5/14]
 **/
@Slf4j
public class FlowableUtil {

    private static String NAME;

    /**
     * 初始化获取实例
     */
    static {
        log.info("FlowableUtil初始化。。。");
        NAME = "FlowableUtil";
    }

    public static String getName(){
        log.info("执行方法：getName");
        return NAME;
    }

    @Override
    protected void finalize() throws Throwable {
        log.info("FlowableUtil finalize。。。");
        super.finalize();
    }
}
