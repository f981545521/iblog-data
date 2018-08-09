package cn.acyou.iblogdata.jsms;

import cn.acyou.iblogdata.utils.RandomUtil;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.ApacheHttpClient;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.account.AccountBalanceResult;
import cn.jsms.api.account.AppBalanceResult;
import cn.jsms.api.common.model.BatchSMSPayload;
import cn.jsms.api.common.model.BatchSMSResult;
import cn.jsms.api.common.model.RecipientPayload;
import cn.jsms.api.schedule.model.ScheduleResult;
import cn.jsms.api.schedule.model.ScheduleSMSPayload;
import cn.jsms.api.template.SendTempSMSResult;
import cn.jsms.api.template.TempSMSResult;
import cn.jsms.api.template.TemplatePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;

import java.util.ArrayList;
import java.util.List;


public class JSMSExample {

	protected static final Logger LOG = LoggerFactory.getLogger(JSMSExample.class);

    private static final String appkey = ConfConstant.appkey;
    private static final String masterSecret = ConfConstant.masterSecret;

    private static final String devKey = ConfConstant.devKey;
    private static final String devSecret = ConfConstant.devSecret;

    private static final String TEST_RECEIVE_PHONE = "18205166207";

    public static void main(String[] args) {
//    	testSendSMSCode();
//    	testSendValidSMSCode();
//        testSendVoiceSMSCode();
//        testSendTemplateSMS();
        testGetAccountSMSBalance();
    }
    
    public static void testSendSMSCode() {
        String validCode = RandomUtil.createRandomStr(4);
        System.out.println("验证码：" + validCode);
    	SMSClient client = new SMSClient(masterSecret, appkey);
    	SMSPayload payload = SMSPayload.newBuilder()
				.setMobileNumber(TEST_RECEIVE_PHONE)
				.setTempId(1).setCode(validCode)
				.build();
    	try {
			SendSMSResult res = client.sendSMSCode(payload);
            System.out.println(res.toString());
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 使用HttpClient 发送短信
     */
    public static void testSendSMSWithIHttpClient() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
        // NettyHttpClient httpClient = new NettyHttpClient(authCode, null, ClientConfig.getInstance());
        // ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
        // 可以切换 HttpClient，默认使用的是 NativeHttpClient
        client.setHttpClient(httpClient);
        // 如果使用 NettyHttpClient，发送完请求后要调用 close 方法
        // client.close();
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(TEST_RECEIVE_PHONE)
                .setTempId(1)
                .build();
        try {
            SendSMSResult res = client.sendSMSCode(payload);
            System.out.println(res.toString());
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     *  msgId : msg_id为调用发送验证码API的返回值
     */
    public static void testSendValidSMSCode() {
    	SMSClient client = new SMSClient(masterSecret, appkey);
		try {
			ValidSMSResult res = client.sendValidSMSCode("01658697-45d9-4644-996d-69a1b14e2bb8", "556618");
            System.out.println(res.toString());
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 50010) {
                // do something
            }
            System.out.println(e.getStatus() + " errorCode: " + e.getErrorCode() + " " + e.getErrorMessage());
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
	}

    /**
     * 发送语音验证
     *  The default value of ttl is 60 seconds.
     */
	public static void testSendVoiceSMSCode() {
	    SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber("13800138000")
                .setTTL(90)
                .build();
        try {
            SendSMSResult res = client.sendVoiceSMSCode(payload);
            LOG.info(res.toString());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        }
    }

    /**
     * 发送模版短信
     */
	public static void testSendTemplateSMS() {
	    SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber("13800138000")
                .setTempId(1)
                .addTempPara("test", "jpush")
                .build();
        try {
            SendSMSResult res = client.sendTemplateSMS(payload);
            LOG.info(res.toString());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        }
    }

    /**
     * 批量发送模版短信
     */
    public static void testSendBatchTemplateSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        List<RecipientPayload> list = new ArrayList<RecipientPayload>();
        RecipientPayload recipientPayload1 = new RecipientPayload.Builder()
                .setMobile("13800138000")
                .addTempPara("code", "638938")
                .build();
        RecipientPayload recipientPayload2 = new RecipientPayload.Builder()
                .setMobile("13800138000")
                .addTempPara("code", "829302")
                .build();
        list.add(recipientPayload1);
        list.add(recipientPayload2);
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        BatchSMSPayload smsPayload = BatchSMSPayload.newBuilder()
                .setTempId(1)
                .setRecipients(list.toArray(recipientPayloads))
                .build();
        try {
            BatchSMSResult result = client.sendBatchTemplateSMS(smsPayload);
            LOG.info("Got result: " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 发送定时短信
     */
    public static void testSendScheduleSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        ScheduleSMSPayload payload = ScheduleSMSPayload.newBuilder()
                .setMobileNumber("13800138000")
                .setTempId(1111)
                .setSendTime("2017-07-31 16:17:00")
                .addTempPara("number", "798560")
                .build();
        try {
            ScheduleResult result = client.sendScheduleSMS(payload);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 更新定时短信
     */
    public static void testUpdateScheduleSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        ScheduleSMSPayload payload = ScheduleSMSPayload.newBuilder()
                .setMobileNumber("13800138000")
                .setTempId(1111)
                .setSendTime("2017-07-31 15:00:00")
                .addTempPara("number", "110110")
                .build();
        try {
            ScheduleResult result = client.updateScheduleSMS(payload, "dsfd");
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 发送批量定时短信
     */
    public static void testSendBatchScheduleSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        List<RecipientPayload> list = new ArrayList<RecipientPayload>();
        RecipientPayload recipientPayload1 = new RecipientPayload.Builder()
                .setMobile("13800138000")
                .addTempPara("number", "638938")
                .build();
        RecipientPayload recipientPayload2 = new RecipientPayload.Builder()
                .setMobile("13800138001")
                .addTempPara("number", "829302")
                .build();
        list.add(recipientPayload1);
        list.add(recipientPayload2);
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        ScheduleSMSPayload smsPayload = ScheduleSMSPayload.newBuilder()
                .setSendTime("2017-07-31 16:00:00")
                .setTempId(1245)
                .setRecipients(list.toArray(recipientPayloads))
                .build();
        try {
            BatchSMSResult result = client.sendBatchScheduleSMS(smsPayload);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 更新发送批量定时短信
     */
    public static void testUpdateBatchScheduleSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        List<RecipientPayload> list = new ArrayList<RecipientPayload>();
        RecipientPayload recipientPayload1 = new RecipientPayload.Builder()
                .setMobile("13800138000")
                .addTempPara("number", "328393")
                .build();
        RecipientPayload recipientPayload2 = new RecipientPayload.Builder()
                .setMobile("13800138001")
                .addTempPara("number", "489042")
                .build();
        list.add(recipientPayload1);
        list.add(recipientPayload2);
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        ScheduleSMSPayload smsPayload = ScheduleSMSPayload.newBuilder()
                .setSendTime("2017-07-31 16:00:00")
                .setTempId(1245)
                .setRecipients(list.toArray(recipientPayloads))
                .build();
        try {
            BatchSMSResult result = client.updateBatchScheduleSMS(smsPayload, "dfs");
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 删除定时短信
     */
    public static void testDeleteScheduleSMS() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        try {
            ResponseWrapper result = client.deleteScheduleSMS("sd");
            LOG.info("Response content: " + result.responseContent + " response code: " + result.responseCode);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 账号余量查询 API
     * dev_balance 全类型短信余量
     * dev_voice 语音短信余量
     * dev_industry 行业短信余量
     * dev_market 营销短信余量
     * 如果字段值返回 null 即为未分配余量
     */
    public static void testGetAccountSMSBalance() {
        SMSClient client = new SMSClient(devSecret, devKey);
        try {
            AccountBalanceResult result = client.getSMSBalance();
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    /**
     * 查询应用余量，应用余量指分配给某个应用单独使用的短信余量。
     */
    public static void testGetAppSMSBalance() {
        SMSClient client = new SMSClient(masterSecret, appkey);
        try {
            AppBalanceResult result = client.getAppSMSBalance();
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }


    public void testCreateTemplate() {
        try {
            SMSClient client = new SMSClient(masterSecret, appkey);
            TemplatePayload payload = TemplatePayload.newBuilder()
                    .setTemplate("您好，您的验证码是{{code}}，2分钟内有效！")
                    .setType(1)
                    .setTTL(120)
                    .setRemark("验证短信")
                    .build();
            SendTempSMSResult result = client.createTemplate(payload);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    // 只有审核不通过状态的模板才允许修改
    public void testUpdateTemplate() {
        try {
            SMSClient client = new SMSClient(masterSecret, appkey);
            TemplatePayload payload = TemplatePayload.newBuilder()
                    .setTempId(12345)
                    .setTemplate("您好，您的验证码是{{code}}，2分钟内有效！")
                    .setType(1)
                    .setTTL(120)
                    .setRemark("验证短信")
                    .build();
            SendTempSMSResult result = client.updateTemplate(payload, 12345);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void testCheckTemplate() {
        try {
            SMSClient client = new SMSClient(masterSecret, appkey);
            TempSMSResult result = client.checkTemplate(144923);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    public void testDeleteTemplate() {
        try {
            SMSClient client = new SMSClient(masterSecret, appkey);
            ResponseWrapper result = client.deleteTemplate(144923);
            LOG.info(result.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }
    
}
