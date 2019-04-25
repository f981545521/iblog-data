package cn.acyou.iblogdata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author youfang
 * @version [1.0.0, 2019-04-24 下午 05:50]
 **/
public class StaticUtil {

    private static final  Properties PROPERTIES = new Properties();
    private static Logger log = LoggerFactory.getLogger(StaticUtil.class);

    static {
        try {
            URL url = StaticUtil.class.getClassLoader().getResource("mongodb.properties");
            if (url != null) {
                log.info("Found 'mongodb.properties' file in local classpath");
                try (InputStream in = url.openStream()) {
                    PROPERTIES.load(in);
                }
            }
        } catch (IOException e) {
            log.info("Could not load 'mongodb.properties' file from local classpath: {}", e);
        }
    }

    /**
     * config file info
     */
    private static class Config {
        /** IP */
        static String ip;
        /** PORT */
        static int port = 27017;
        /** DATABASE */
        static String database;
        /** 初始化连接数 */
        static int connectionsPerHost = 10;
        /** 最大等待时间 */
        static int maxWaitTime = 120000;
        /** 超时时间 */
        static int connectTimeout = 0;

        static {
            ip = PROPERTIES.getProperty("mongo_ip");
            database = PROPERTIES.getProperty("mongo_database");
            int mongoPort = Integer.parseInt(PROPERTIES.getProperty("mongo_port"));
            if (mongoPort != -1) {
                port = mongoPort;
            }
            int mongoConnectionsPerHost = Integer.parseInt(PROPERTIES.getProperty("mongo_connections_per_host"));
            if (connectionsPerHost != -1) {
                connectionsPerHost = mongoConnectionsPerHost;
            }
            int mongoMaxWaitTime = Integer.parseInt(PROPERTIES.getProperty("mongo_max_wait_time"));
            if (mongoMaxWaitTime != -1) {
                maxWaitTime = mongoMaxWaitTime;
            }
            int mongoConnectTimeout = Integer.parseInt(PROPERTIES.getProperty("mongo_connect_timeout"));
            if (mongoConnectTimeout != -1) {
                connectTimeout = mongoConnectTimeout;
            }
            log.info("———————— StaticUtil 加载完成 ———————");
        }
    }

    public static void staticMethod() {
        log.info("执行方法 staticMethod() 完成");
        System.out.println(Config.ip + ":" + Config.port);
    }

}
