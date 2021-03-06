package cn.acyou.iblogdata.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 下午 04:52]
 **/
@Slf4j
public class MyHandler extends TextWebSocketHandler {
    //在线用户列表
    private static final Map<Integer, WebSocketSession> users;
    //用户标识
    private static final String CLIENT_ID = "userId";

    static {
        users = new ConcurrentHashMap<>();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WebSocketMessage respMessage = new TextMessage("你好，客户端：服务端已经收到你发的消息！" + message);
        session.sendMessage(respMessage);
        log.info("Web Socket Handler : " + message.getPayload());
        super.handleTextMessage(session, message);
    }

    /**
     * 建立连接后触发的回调
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("成功建立连接");
        Integer userId = getClientId(session);
        if (userId != null) {
            users.put(userId, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            log.info(session.toString());
        }
        super.afterConnectionEstablished(session);
    }

    /**
     * 收到消息时触发的回调
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 处理接收的Pong类型的消息
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    /**
     * 传输消息出错时触发的回调
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(getClientId(session));
        log.error("连接出错");
    }

    /**
     * 断开连接后触发的回调
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.error("连接已关闭：" + status);
        users.remove(getClientId(session));
    }

    /**
     * 是否处理分片消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get(CLIENT_ID);
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 发送信息给指定用户
     */
    public boolean sendMessageToUser(Integer clientId, TextMessage message) {
        if (users.get(clientId) == null) return false;
        WebSocketSession session = users.get(clientId);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<Integer> clientIds = users.keySet();
        WebSocketSession session = null;
        for (Integer clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }

}
