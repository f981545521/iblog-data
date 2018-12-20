package cn.acyou.iblogdata.runner;

import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Springboot给我们提供了两种“开机启动”某些方法的方式：ApplicationRunner和CommandLineRunner。
 * <p>
 * 这两种方法提供的目的是为了满足，在项目启动的时候立刻执行某些方法。我们可以通过实现ApplicationRunner和CommandLineRunner，来实现，他们都是在SpringApplication 执行之后开始执行的。
 * CommandLineRunner接口可以用来接收字符串数组的命令行参数，ApplicationRunner 是使用ApplicationArguments 用来接收参数的，貌似后者更牛逼一些。
 *
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 11:03]
 **/
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... var1) {
        log.info("MyCommandLineRunner was started!");

        log.warn("测试 ----------- 警告日志");
        log.error("测试 ----------- 错误日志");

        //测试MyFactoryBean
        StudentService studentService = (StudentService) context.getBean("fbStudentService");
        studentService.sayThanks();

        //testSingletonThread();
    }

    /**
     * 测试单例模式线程安全性
     */
    public void testSingletonThread() {
        //构造一个用给定计数初始化的 CountDownLatch。
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getId() + "准备就绪");
                try {
                    latch.await();//使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisUtil.print();
                System.out.println("线程" + Thread.currentThread().getId() + "执行完成！");
            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();//递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
        System.out.println("主线程完成！继续执行");
    }
}
