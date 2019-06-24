package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.service.CommonService;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.hutool.core.util.RandomUtil;
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
 * @author youfang
 * @version [1.0.0, 2019-06-24 17:04]
 * @since [ERP服务]
 **/
@Controller
@RequestMapping("concurrency")
public class ConcurrencyTestController extends BaseController {

    @Autowired
    private CommonService commonService;

    private static final String[] SEQ_NAME_ARRAY = new String[]{"ddddxxx_seq", "ddddxxx2_seq", "ddddxxx3_seq"};
    private static final Lock lock = new ReentrantLock();

    @RequestMapping(value = "start", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo start() {

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
                int i1 = RandomUtil.randomInt(0, 3);
                lock.lock();
                Long ddddxxx_seq = commonService.getSequence2(SEQ_NAME_ARRAY[0]);
                System.out.println("线程" + Thread.currentThread().getId() + "执行完成：" + ddddxxx_seq);
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
        return ResultInfo.success();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            int i1 = RandomUtil.randomInt(0, 3);
            System.out.println(i1);
        }
    }
}
