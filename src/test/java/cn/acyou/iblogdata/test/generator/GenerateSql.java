package cn.acyou.iblogdata.test.generator;

import cn.acyou.iblogdata.utils.RandomUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author youfang
 * @version [1.0.0, 2019-08-12 19:47]
 **/
public class GenerateSql {
    public static void main(String[] args) throws Exception{
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();//获取当前用户桌面路径
        File directory = new File(path + "\\user.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        int year = 2014;
        for (int i = 0; i < 20000; i++) {
            String sex = RandomUtil.randomSex();
            int sexInt = 2;
            if (sex.equals("男")){
                sexInt = 1;
            }
            String idNo = "";
            do {
                idNo = RandomUtil.randomIdCardNo(sex.equals("男"));
            } while (idNo.length() != 18);

            if (i%5000==0){
                year ++;
            }
            String yearData = year + "-" + RandomUtil.nextInt(12, 2) + "-" + String.format("%02d", RandomUtil.nextInt(28)) + " 00:00:00";

            String sql = "INSERT INTO `e_user` (`ID`, `IS_DELETE`, `CREATE_TIME`, `UPDATE_TIME`, `PASSWORD`, `STUDENT_NAME`, `SEX`, `CARD_CODE`, `ONE_CARD`, " +
                    "`PHONE`, `BIRTHDAY`, `ADDRESS`, `EMAIL`, `NATION`, `PHOTO_URL`, `USER_TYPE`, `BY_FACULTY_ID`, `BY_MAJOR_ID`, `BY_CLASS_ID`, `FACULTY_NAME`, `MAJOR_NAME`, `CLASS_NAME`, `CLASS_NO`, `STUDENT_CODE`, `ENTRANCE_DATE`, `POSITION`, `JOB_NUMBER`, `GRADE`, `USER_ROLE`, `V_CODE`)" +
                    " VALUES (DEFAULT, '0', '2019-08-08 00:00:00', '2019-08-08 00:00:00', '', '"+ RandomUtil.randomUserName() +"', '"+sexInt+"', '"+idNo+"', '"+RandomUtil.createRandom(true, 4)+
                    "', '"+RandomUtil.randomTelephone()+"', '2019-06-12 00:00:00', '南京', '211@qq.com', '汉族', 'http://192.168.1.188:8585/images/2019-06/1560492295352_121.png'," +
                    " '1', '1', '0', '3', '信息系', '软件技术', '软件31班', '', '"+RandomUtil.createRandom(true, 4)+"', '"+yearData+"', '', '', '2', '0', '');";

            //String sql = "INSERT e_user (STUDENT_NAME,SEX,ONE_CODE,CARD_CODE,PHONE,NATION,FACULTY_NAME,MAJOR_NAME," +
            //        "CLASS_NAME,ENTRANCE_DATE,STUDENT_CODE) VALUES ('"+ RandomUtil.randomUserName() +"','"+sex+"',"+RandomUtil.createRandom(true, 4)+","+idNo+","+RandomUtil.getTelephone()+
            //        ",'汉族','信息系','软件技术','软件31班','"+yearData+"',"+RandomUtil.createRandom(true, 4)+");";


            pw.write(sql);
            pw.write("\r\n");
        }
        pw.close();
    }
}
