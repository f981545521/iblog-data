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
 * MyBatis通用框架
 * @author  Mr Huang
 * @version  [1.0.0, 2017年11月23日]
 * @since  [爱自由/公用DAO]
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
