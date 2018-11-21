package cn.acyou.iblogdata.utils.pay;

import cn.acyou.iblogdata.utils.XMLParser;

import java.util.Map;

/**
 * @author youfang
 * @version [1.0.0, 2018-11-17 下午 03:31]
 **/
public class WxPayUtilTest {

    public static void main(String[] args) throws Exception {
        String xmlStr = "<xml><appid><![CDATA[wx8c6b4ebb53edf3f2]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1485566362]]></mch_id>\n" +
                "<nonce_str><![CDATA[XQGqngmTESslyHkkaYlDGLAUr3WJc4bm]]></nonce_str>\n" +
                "<openid><![CDATA[oxG1I1pgHfa_z791R8P3Wl_dpvo8]]></openid>\n" +
                "<out_trade_no><![CDATA[PL201811171447167313828]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[5D46FF256542604DBD1C503778A822CC]]></sign>\n" +
                "<time_end><![CDATA[20181117144726]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000221201811173967024658]]></transaction_id>\n" +
                "</xml>";
        Map<String, String> xmlMap = XMLParser.getMapFromXML(xmlStr);
        String key = "UZUIJUwsZyY9RadcnpZ3YljojUKgLGCV";
        //签名
        String sign = WXPayUtil.generateSignature(xmlMap, key);
        System.out.println(xmlMap.get("sign"));
        System.out.println(sign);
    }
}
