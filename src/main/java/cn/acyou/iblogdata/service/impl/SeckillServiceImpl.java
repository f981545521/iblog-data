package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblogdata.entity.SeckillProducts;
import cn.acyou.iblogdata.service.SeckillService;
import cn.acyou.iblogdata.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 上午 10:41]
 **/
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource(name = "seckillProducts")
    private Map<Long, SeckillProducts> seckillProductsMap;

    @Override
    public Result<String> doSeckillProduct(Long productId, String phone) {
        SeckillProducts products = seckillProductsMap.get(productId);
        if (products == null) {
            return Result.error("商品不存在！");
        }
        return doOutStock(products);
    }

    private synchronized Result<String> doOutStock(SeckillProducts seckillProducts) {
        log.info("等待出库操作完成...");
        Long productsStock = seckillProducts.getProductsStock();
        if (productsStock <= 0) {
            return Result.error("商品库存不足！");
        }
        seckillProducts.setProductsStock(--productsStock);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("出库操作完成！当前库存：" + productsStock);
        return Result.success("恭喜你，秒杀成功！");
    }
}
