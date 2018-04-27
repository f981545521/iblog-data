/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.acyou.iblogdata.commons;


import java.io.Serializable;
import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T extends Po, PK extends Serializable> {
    int saveSelective(T model);//持久化

    int save(List<T> models);//批量持久化

    int deleteById(PK id);//通过主鍵刪除

    int deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”

    int updateByPrimaryKeySelective(T model);//更新

    int updateByPrimaryKey(T model);//更新

    T findById(PK id);//通过ID查找

    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”

    List<T> findAll();//获取所有

    int selectCount(T v);

}
