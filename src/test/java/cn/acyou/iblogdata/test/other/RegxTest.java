package cn.acyou.iblogdata.test.other;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author youfang
 * @version [1.0.0, 2018-9-3 下午 09:19]
 **/
public class RegxTest {

    @Test
    public void test(){
        Pattern p2 = Pattern.compile(
                "^\\s*Duration: (\\d\\d):(\\d\\d):(\\d\\d)\\.(\\d).*$",
                Pattern.CASE_INSENSITIVE);
        String line = " Duration: 00:05:58.01, start: 0.025057, bitrate: 128 kb/s";
        Matcher m = p2.matcher(line);
        if (m.matches()){
            System.out.println(m.group(1));
        }
    }

    @Test
    public void test12(){
        Map<String, String> map = new HashMap<>();
        map.put("-metadata:s:v", "rotate=\"0\"");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet){
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
