package cn.acyou.iblogdata.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 11:02]
 **/
@Slf4j
@Component
public class ApplicationInitRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments var1) {
        log.info("ApplicationInitRunner ");
    }


}
