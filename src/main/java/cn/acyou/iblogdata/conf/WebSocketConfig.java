package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.aop.WebSocketInterceptor;
import cn.acyou.iblogdata.websocket.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 下午 04:56]
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler").addInterceptors(new WebSocketInterceptor());
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}
