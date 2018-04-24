/*
 * 文 件 名:  MyBatisMapperScannerConfig.java
 * 版    权:  Copyright 2017 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  通用框架
 * 版    本： <1.0.0> 
 * 创 建 人:  Mr Huang
 * 创建时间:  2017年11月23日
 */
package cn.acyou.iblogdata.conf;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @author youfang
 * @date 2018-04-24 13:21
 */
@Configuration
@AutoConfigureAfter(MyBatisConfigurer.class)
public class MyBatisMapperScannerConfig {
    
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.acyou.iblogdata.dao");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
