package cn.acyou.iblogdata.jmessage.example;

import cn.acyou.iblogdata.jsms.ConfConstant;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.message.MessageListResult;
import cn.jmessage.api.reportv2.GroupStatListResult;
import cn.jmessage.api.reportv2.MessageStatListResult;
import cn.jmessage.api.reportv2.ReportClient;
import cn.jmessage.api.reportv2.UserStatListResult;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * IM REST Report V2
 * 消息历史
 */
public class ReportExample {

    private static Logger LOG = LoggerFactory.getLogger(ReportExample.class);
    private static final String appkey = ConfConstant.appkey;
    private static final String masterSecret = ConfConstant.masterSecret;
    private static ReportClient mClient = new ReportClient(appkey, masterSecret);

    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        //getMessageHistory("youfang1");
        getMessageList();
    }

    /**
     * 获取用户消息
     * 目前只保存最近60天消息
     * @param userName
     */
    public static void getMessageHistory(String userName){
        DateTime nowTime = new DateTime();
        DateTime nowAgo = new DateTime().minusDays(7);
        try {
            MessageListResult messageList = mClient.v2GetUserMessages(userName, 100, nowAgo.toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
            System.out.println(Arrays.toString(messageList.getMessages()));
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }
    /**
     * 获取消息
     */
    public static void getMessageList(){
        DateTime nowTime = new DateTime();
        DateTime nowAgo = new DateTime().minusDays(7);
        try {
            MessageListResult messageList = mClient.v2GetMessageList(100, nowAgo.toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
            System.out.println(Arrays.toString(messageList.getMessages()));
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void testGetUserStat() {
        try {
            UserStatListResult result = mClient.getUserStatistic("2016-11-08", 3);
            LOG.info("Got result: " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void testGetMessageStat() {
        try {
            MessageStatListResult result = mClient.getMessageStatistic("DAY", "2016-11-08", 3);
            LOG.info("Got result: " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void testGetGroupStat() {
        try {
            GroupStatListResult result = mClient.getGroupStatistic("2016-11-08", 3);
            LOG.info("Got result: " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

}
