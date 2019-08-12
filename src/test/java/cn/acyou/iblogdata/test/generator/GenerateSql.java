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
        File directory = new File(path + "\\user.txt");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < 10000; i++) {
            String sex = RandomUtil.randomSex();
            String idNo = RandomUtil.getIdNo(sex.equals("男"));

            String sql = "INSERT e_user (STUDENT_NAME,SEX,ONE_CODE,CARD_CODE,PHONE,NATION,FACULTY_NAME,MAJOR_NAME," +
                    "CLASS_NAME,STUDENT_CODE) VALUES ('"+ RandomUtil.randomUserName() +"','"+sex+"',"+RandomUtil.createRandom(true, 4)+","+idNo+","+RandomUtil.getTelephone()+",'汉族','信息系','软件技术','软件31班',"+RandomUtil.createRandom(true, 4)+");";
            pw.write(sql);
            pw.write("\r\n");
        }
        pw.close();
    }
}
