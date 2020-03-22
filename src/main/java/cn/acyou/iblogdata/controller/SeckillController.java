package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.service.SeckillService;
import cn.acyou.iblogdata.utils.Result;
import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 上午 10:54]
 **/
@Controller
@RequestMapping("/seckill")
@Api(description = "秒杀演示")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    private static final Lock lock = new ReentrantLock();

    @RequestMapping(value = "/doSeckill", method = {RequestMethod.GET})
    @ResponseBody
    public Result doSeckill() {
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                System.out.println("线程" + Thread.currentThread().getId() + "准备完成！");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String phone = RandomUtil.randomNumbers(11);
                lock.lock();
                Result<String> seckillResult = seckillService.doSeckillProduct(10000L, phone);
                System.out.println("线程" + Thread.currentThread().getId() + "执行完成：" + seckillResult);
                System.out.println("执行结果：" + seckillResult);
                lock.unlock();
            });
        }

        try {
            System.out.println("等待线程就绪...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("主线程完成");
        executor.shutdown();
        return Result.success();
    }
}
