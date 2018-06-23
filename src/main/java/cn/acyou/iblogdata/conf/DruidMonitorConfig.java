package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.listener.DruidMonitorListener;
import com.alibaba.druid.support.monitor.MonitorClient;
import com.alibaba.druid.support.monitor.dao.MonitorDao;
import com.alibaba.druid.support.monitor.dao.MonitorDaoJdbcImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-23 上午 11:18]
 **/
@Configuration
public class DruidMonitorConfig {


    @Bean
    public MonitorDao monitorDao(@Qualifier("dataSource")DataSource dataSource){
        MonitorDaoJdbcImpl dao = new MonitorDaoJdbcImpl();
        dao.setDataSource(dataSource);
        return dao;
    }

    @Bean
    public MonitorClient monitorClient(@Qualifier("monitorDao")MonitorDao monitorDao){
        MonitorClient client = new MonitorClient();
        client.setDao(monitorDao);
        return client;
    }

    @Bean
    public ServletListenerRegistrationBean<DruidMonitorListener> monitorListenerRegistration(@Qualifier("monitorClient")MonitorClient client) {
        DruidMonitorListener monitorListener = new DruidMonitorListener();
        monitorListener.setClient(client);
        return new ServletListenerRegistrationBean<>(monitorListener);
    }
}