package cn.acyou.iblogdata.test.tests;

import org.apache.log4j.Logger;

/**
 * @author youfang
 * @version [1.0.0, 2018-11-07 上午 09:54]
 * @since [天天健身/运动模块]
 **/
public class TenToHexDemo {
    private static final Logger LOGGER = Logger.getLogger(TenToHexDemo.class);
    public static void main(String[] args) {
        //定义一个十进制值
        int valueTen = 305419896;
        //将其转换为十六进制并输出
        String strHex = Integer.toHexString(valueTen);
        LOGGER.info(valueTen + " [十进制]---->[十六进制] " + strHex);
        //将十六进制格式化输出
        String strHex2 = String.format("%08x",valueTen);
        LOGGER.info(valueTen + " [十进制]---->[十六进制] " + strHex2);

        LOGGER.info("==========================================================");
        //定义一个十六进制值
        String strHex3 = "00001322";
        //将十六进制转化成十进制
        int valueTen2 = Integer.parseInt(strHex3,16);
        LOGGER.info(strHex3 + " [十六进制]---->[十进制] " + valueTen2);

        LOGGER.info("==========================================================");
        //可以在声明十进制时，自动完成十六进制到十进制的转换
        int valueHex = 0x00001322;
        LOGGER.info("int valueHex = 0x00001322 --> " + valueHex);
    }
}
