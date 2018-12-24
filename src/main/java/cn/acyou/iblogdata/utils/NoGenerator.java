package cn.acyou.iblogdata.utils;

import java.util.UUID;

/**
 * 编号生成器
 * @author youfang
 * @version [1.0.0, 2018-12-21 下午 05:33]
 **/
public class NoGenerator {
    public static String getOrderIdByUUId(){
        return getOrderIdByUUId(1);
    }

    /**
     * 使用UUID生成单号
     * @param machineId 最大支持1-9个集群机器部署
     * @return 单号
     */
    public static String getOrderIdByUUId(int machineId){
        int hashCodev = UUID.randomUUID().toString().hashCode();
        if(hashCodev < 0){
            //有可能是负数
            hashCodev = -hashCodev;
        }
        //"%015d"的意思：0代表不足位数的补0，这样可以确保相同的位数，15是位数也就是要得到到的字符串长度是15，d代表数字。
        return machineId + String.format("%015d", hashCodev);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(NoGenerator.getOrderIdByUUId());
        }
    }

}
