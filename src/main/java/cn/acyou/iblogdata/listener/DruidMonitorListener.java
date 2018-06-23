package cn.acyou.iblogdata.listener;

import com.alibaba.druid.support.monitor.MonitorClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DruidMonitorListener implements ServletContextListener {

	private MonitorClient client;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (client != null) {
			client.start();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (client != null) {
			client.stop();
			this.client = null;
		}
	}

	public MonitorClient getClient() {
		return client;
	}

	public void setClient(MonitorClient client) {
		this.client = client;
	}
}
