package cn.acyou.iblogdata.dao;

import org.apache.ibatis.annotations.Param;

/**
 *     int copyCarCategory(@Param("carCategory")TbCarCategory carCategory, @Param("newOrgId") String newOrgId);
 *     多个参数使用@Param注解，可以支持.取值
 * @author youfang
 * @version [1.0.0, 2018-12-21 下午 03:21]
 **/
public interface BaseMapper {

    /**
     * 生成UUID主键
     * @return uuid
     */
    String getUUid();

    /**
     * 生成单号
     * @param billType 单据类型
     * @param billDate 单据日期
     * @return 单号
     */
    String getBillNo(@Param("billType") String billType, @Param("billDate") String billDate);

    /**
     * 获得一个全局唯一的数作为订单号的追加
     */
    Long getBuildOnlyNumber(@Param("seq_name") String seq_name);

    void createSequence(@Param("seq_name") String seqName);
}
