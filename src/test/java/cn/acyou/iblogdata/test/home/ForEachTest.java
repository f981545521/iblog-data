package cn.acyou.iblogdata.test.home;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 *
 *  M3U8文件替换
 *  查找目标：  (\d+.ts)
 *  替换值：   ./ts/\1
 *
 *
 * .\ffmpeg.exe -i .\index.m3u8 -c copy -bsf:a aac_adtstoasc ALL.mp4
 * @author youfang
 * @version [1.0.0, 2019-11-30 下午 04:37]
 **/
public class ForEachTest {
    //%04d
    private static String baseUrl = "https://nsb236-ls-dfw.resource04.com/2019/11/29/6c42a301/d29eacab/{0}.ts";

    public static void main(String[] args) throws Exception {
        File file = new File("G:\\server\\QQMusic_\\M3U8\\file.txt");
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i <= 347; i++) {
            String cur = MessageFormat.format(baseUrl, String.format("%04d", i));
            System.out.println(cur);
            printWriter.write(cur);
            printWriter.write("\r\n");
        }
        printWriter.flush();
        printWriter.close();
    }



}
