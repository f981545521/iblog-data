package cn.acyou.iblogdata.constant;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:06]
 **/
public class AppRedisKey {

    /**
     * spring cache
     */
    public final static String SPRING_CACHE = "SPRING:CACHE";
    public final static Long SPRING_CACHE_DEFAULT_EXPIRETIME = 1200L;


    /**
     * hash存储
     * hashkey-学生ID
     * hashValue - 学生信息
     */
    public final static String IBLOGDATA_STUDENT_INFO = "IBLOGDATA:STUDENT:INFO";
}
