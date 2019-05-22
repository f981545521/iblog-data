package cn.acyou.iblogdata.test.poi;

import cn.acyou.iblogdata.utils.DocUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-22 14:01]
 **/
public class TemplatePoi {
    public static List<String> variableSet = new ArrayList<>();
    public static String path = "D:\\公证业务模板\\";
    public static void main(String[] args) {
        try {
            getFiles(path);
            if (CollectionUtils.isNotEmpty(variableSet)){
                File xls = new File("D:/TemplateVariablesList.txt");
                FileWriter fw = new FileWriter(xls);
                PrintWriter pw = new PrintWriter(fw);
                for (String var: variableSet){
                    pw.write(var);
                    pw.write("\r\n");
                }
                pw.close();
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static List<String> extractTemplateVariables(String str) {
        List<String> pattenList = new ArrayList<>();
        String pattern = "(?<=\\{)[^}]*(?=\\})";
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher mat = r.matcher(str);
        while (mat.find()){
            String val = mat.group(0);
            pattenList.add("{".concat(val).concat("}"));
        }
        return pattenList;
    }


    public static void getFiles(String path) throws Exception{
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getPath());
                    //如果还是文件夹 递归获取里面的文件 文件夹
                    getFiles(files[i].getPath());
                } else {
                    System.out.println("文件：" + files[i].getPath());
                    String filePath = files[i].getPath();
                    File docFile = new File(filePath);
                    String content = DocUtil.doc2String(docFile);
                    variableSet.add("文件：" + files[i].getPath());
                    variableSet.addAll(extractTemplateVariables(content));
                }
            }
        } else {
            System.out.println("文件：" + file.getPath());
        }
    }
}
