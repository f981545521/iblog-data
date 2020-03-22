package cn.acyou.iblogdata.service;

import cn.acyou.iblogdata.utils.Result;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 上午 10:41]
 **/
public interface SeckillService {

    Result<String> doSeckillProduct(Long productId, String phone);
}
