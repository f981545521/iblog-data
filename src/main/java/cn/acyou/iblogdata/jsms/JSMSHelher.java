package cn.acyou.iblogdata.jsms;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import lombok.extern.slf4j.Slf4j;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-09 下午 03:35]
 **/
@Slf4j
public class JSMSHelher {
    private static final String appkey = ConfConstant.appkey;
    private static final String masterSecret = ConfConstant.masterSecret;
    private static final String devKey = ConfConstant.devKey;
    private static final String devSecret = ConfConstant.devSecret;

    private static final String TEST_RECEIVE_PHONE = "18205166207";

    private static SMSClient client;

    static {
        client = new SMSClient(masterSecret, appkey);
    }

    public static void main(String[] args) {
        //String messageId = sendSMSCode(TEST_RECEIVE_PHONE);
        validSMSCode("562275320266", "819895");
    }

    /**
     * 发送验证码
     * @param toPhone 发送给xxx
     * @return  messageId 用来验证短信验证码的凭证
     */
    public static String sendSMSCode(String toPhone) {
        String messageId = "";
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(toPhone)
                .setTempId(ConfConstant.JSMS.template)
                .build();
        try {
            SendSMSResult res = client.sendSMSCode(payload);
            log.info("msgId : " + res.getMessageId());
            messageId = res.getMessageId();
            System.out.println(res.toString());
            log.info(res.toString());
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
        return messageId;
    }


    /**
     * 验证验证码是否有效
     * @param messageId msg_id为调用发送验证码API的返回值
     * @param input 输入的内容
     */
    public static void validSMSCode(String messageId, String input) {
        try {
            ValidSMSResult res = client.sendValidSMSCode(messageId, input);
            System.out.println(res.toString());
            log.info(res.toString());
            if (res.getIsValid()){
                //验证通过
            }else {
                //验证失败
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            if (e.getErrorCode() == 50011) {
                log.info("验证码过期了！");
            }
            if (e.getErrorCode() == 50012) {
                log.info("验证码已经被使用！");
            }
            log.info("Error Message: " + e.getMessage());
        }
    }




}
