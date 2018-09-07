package cn.acyou.iblogdata.test.util;

import cn.acyou.iblogdata.entity.Product;
import cn.acyou.iblogdata.test.base.BaseTest;
import cn.acyou.iblogdata.utils.RedisLock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author youfang
 * @version [1.0.0, 2018-09-07 下午 03:38]
 **/
public class RedisLockTest1 extends BaseTest {

    @Autowired
    private RedisLock redisLock;

    @Test
    public void test21(){
        Product product = new Product(10001L, "气球", 1000);
        String key = RedisLock.LOCK_PREFIX + product.getId();
        try {
            if (redisLock.lock(key)) {
                product.inStock(500);
                System.out.println(product.getStockNumber());
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            redisLock.unlock(key);
        }
    }
}
