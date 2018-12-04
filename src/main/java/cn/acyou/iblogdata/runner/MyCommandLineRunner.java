package cn.acyou.iblogdata.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Springboot给我们提供了两种“开机启动”某些方法的方式：ApplicationRunner和CommandLineRunner。
 *
 * 这两种方法提供的目的是为了满足，在项目启动的时候立刻执行某些方法。我们可以通过实现ApplicationRunner和CommandLineRunner，来实现，他们都是在SpringApplication 执行之后开始执行的。
 * CommandLineRunner接口可以用来接收字符串数组的命令行参数，ApplicationRunner 是使用ApplicationArguments 用来接收参数的，貌似后者更牛逼一些。
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 11:03]
 **/
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... var1) throws Exception{
        log.info("MyCommandLineRunner was started!");

        log.warn("测试 ----------- 警告日志");
        log.error("测试 ----------- 错误日志");
    }
}
