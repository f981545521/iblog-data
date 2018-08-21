package cn.acyou.iblogdata.jmessage;

import cn.acyou.iblogdata.jsms.ConfConstant;
import cn.acyou.iblogdata.utils.Md5Util;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.ApacheHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.message.MessageBody;
import cn.jmessage.api.message.MessageListResult;
import cn.jmessage.api.message.SendMessageResult;
import cn.jmessage.api.reportv2.ReportClient;
import cn.jmessage.api.user.UserInfoResult;
import cn.jmessage.api.user.UserListResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 上午 11:58]
 **/
@Slf4j
@Component
public class JMessageHelper {

    private static final String appkey = ConfConstant.appkey;
    private static final String masterSecret = ConfConstant.masterSecret;
    private static final long test_gid = 10004809;
    private static JMessageClient client;

    static {
        client = new JMessageClient(appkey, masterSecret);
        log.info("Client ----> 初始化完成");
    }

    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        //getUserInfo("youfang");
        //getUserInfo("acyou1");
        String memberId = "10001";
        String secret = memberId + "xiaojuanniao";
        String password = Md5Util.getMD5(secret);
        System.out.println("密码：" + password);
        List<RegisterInfo> users = new ArrayList<RegisterInfo>();
        RegisterInfo user = RegisterInfo.newBuilder().setUsername(memberId).setPassword(password).build();
        RegisterInfo user1 = RegisterInfo.newBuilder().setUsername("youfang2").setPassword("youfang2").build();
        users.add(user);
        //users.add(user1);
        //registerUsers(users);

        sendSingleTextByAdmin("100032", "<span class='green'>伊小姐</span>刚刚发布了一条位于背景的啾啾 <a href='#'>立即查看 &gt;</a>");
        //getUsers();
    }

    /**
     * 新增用户
     */
    public void registerUsers(HttpServletRequest request, List<RegisterInfo> registerInfoList) {
        String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
        // 用 ApacheHttpClient 代替默认的 NativeHttpClient
        client.setHttpClient(httpClient);
        try {
            RegisterInfo[] regUsers = new RegisterInfo[registerInfoList.size()];
            String res = client.registerUsers(registerInfoList.toArray(regUsers));
            log.info(res);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
    }

    public static UserInfoResult getUserInfo(String username) {
        UserInfoResult userInfo = null;
        try {
            userInfo = client.getUserInfo(username);
            log.info(userInfo.toString());
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
        return userInfo;
    }

    public static void getUsers() {
        try {
            UserListResult res = client.getUserList(0, 30);
            log.info(res.getOriginalContent());
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 获取用户消息
     * 目前只保存最近60天消息
     * @param userName
     */
    public static MessageListResult getMessageHistory(String userName){
        ReportClient mClient = new ReportClient(appkey, masterSecret);
        MessageListResult result = null;
        DateTime nowTime = new DateTime();
        DateTime nowAgo = new DateTime().minusDays(7);
        try {
            result = mClient.v2GetUserMessages(userName, 100, nowAgo.toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
        } catch (APIConnectionException e) {
            e.printStackTrace();
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
        return result;
    }


    /**
     * 通过管理员发送消息
     *
     */
    public static void sendSingleTextByAdmin(String targetId, String content) {
        try {
            MessageBody body = MessageBody.text(content);
            SendMessageResult result = client.sendSingleTextByAdmin(targetId, "10001", body);
            log.info(String.valueOf(result.getMsg_id()));
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
    }
}
