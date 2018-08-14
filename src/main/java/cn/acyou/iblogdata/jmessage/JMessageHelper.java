package cn.acyou.iblogdata.jmessage;

import cn.acyou.iblogdata.jsms.ConfConstant;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.ApacheHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.user.UserInfoResult;
import cn.jmessage.api.user.UserListResult;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 上午 11:58]
 **/
@Slf4j
public class JMessageHelper {

    private static final String appkey = ConfConstant.appkey;
    private static final String masterSecret = ConfConstant.masterSecret;
    private static final long test_gid = 10004809;
    private static JMessageClient client;

    static {
        client = new JMessageClient(appkey, masterSecret);
        log.info("Client ----> 初始化完成");
    }

    public static void main(String[] args) {
        //getUserInfo("youfang");
        //getUserInfo("acyou1");

        //List<RegisterInfo> users = new ArrayList<RegisterInfo>();
        //RegisterInfo user = RegisterInfo.newBuilder().setUsername("youfang1").setPassword("youfang1").build();
        //RegisterInfo user1 = RegisterInfo.newBuilder().setUsername("youfang2").setPassword("youfang2").build();
        //users.add(user);
        //users.add(user1);
        //registerUsers(users);


        getUsers();
    }

    /**
     * 新增用户
     */
    public static void registerUsers(List<RegisterInfo> registerInfoList) {
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
}
