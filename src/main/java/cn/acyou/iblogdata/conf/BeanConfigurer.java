package cn.acyou.iblogdata.conf;

import cn.acyou.iblog.orika.OrikaMapper;
import cn.acyou.iblogdata.utils.StudentConfig;
import cn.acyou.iblogdata.utils.StudentConfig2;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author youfang
 * @date 2018-05-17 下午 09:06
 */
@Configuration
public class BeanConfigurer {

    @Bean(name = "studentConfigPrimary")
    @Primary
    @DependsOn(value = "studentConfig2")
    @Order(value = 12)
    public StudentConfig studentConfig(){
        return new StudentConfig(1, "看家的", 23);
    }

    @Bean(name = "studentConfig2")
    @Lazy(true)
    @Order(value = 1)
    public StudentConfig2 studentConfig2(){
        return new StudentConfig2(1, "看家的", 23);
    }

    @Bean
    public OrikaMapper orikaMapper(){
        return new OrikaMapper();
    }


}
