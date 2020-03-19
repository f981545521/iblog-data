package cn.acyou.iblogdata.commons.tkmapper;

import cn.acyou.iblogdata.commons.tkmapper.provide.CommonMapperProvider;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * 公共Mapper接口
 *
 * @param <T> 不能为空
 * @author youfang
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface CommonMapper<T> {

    /**
     * 获取下一个排序值
     *
     * 表中必须有sort字段
     * 实体中必须有@Id注解
     *
     * @return 下一个排序值
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "dynamicSQL")
    Integer getNextSortNumber();

}