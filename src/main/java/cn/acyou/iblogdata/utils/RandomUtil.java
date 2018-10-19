package cn.acyou.iblogdata.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-07 下午 05:01]
 **/
public class RandomUtil {

    /**
     *
     * @param numberFlag 是否数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        StringBuilder retStr = new StringBuilder();
        String strTable = numberFlag ? "1234567890"
                : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = new StringBuilder();
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr.append(strTable.charAt(intR));
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr.toString();
    }

    /**
     * 递归生成指定长度的随机字符串
     * @param length 长度
     * @return 字符串
     */
    public static String createRandomStr(int length) {
        String str = UUID.randomUUID().toString().replace("-","");
        if (length < str.length()){
            return str.substring(str.length() - length);
        }
        return str + createRandomStr(length - str.length());
    }

    /**
     * 随机 0 or 1
     * @return  0 or 1
     */
    public static int random01(){
        Random r = new Random();
        return r.nextInt(2);
        //return Math.random() > 0.5 ? 1 : 0;
    }

    public static void main(String[] args){
        for (int i = 0; i< 100;i++){
            System.out.println(random01());
        }
        //System.out.println(UUID.randomUUID().toString().replace("-","").length());
        //String result = createRandomStr(180);
        //System.out.println(result + ":" + result.length());
    }
}
