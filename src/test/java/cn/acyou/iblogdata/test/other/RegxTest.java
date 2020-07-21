package cn.acyou.iblogdata.test.other;

import org.junit.Test;

import java.util.Arrays;
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
    public void test22322313123() {
        String s = "Duplicate entry '我是试试水' for key 'idx_param_config_code'";
        Pattern p = Pattern.compile("'([^']+)'", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        matcher.find();
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        matcher.find();
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
    }
    @Test
    public void test2232231w3123() {
        String s = "Duplicate entry 'DEMO:KEY' for key 'idx_param_config_code'";
        String regex = "'([^']+)'";

        //String[] split = s.split(regex);
        //System.out.println(split);
        //System.out.println();
        // 创建 Pattern 对象
        System.out.println(s.matches("Duplicate entry(.*)"));
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(s);
        System.out.println(m.groupCount());
        if (m.find()) {
            System.out.println("Found value: " + m.group(1) );
        } else {
            System.out.println("NO MATCH");
        }

    }


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

    @Test
    public void test3(){
        //Pattern类用于创建一个正则表达式,也可以说创建一个匹配模式,
        //它的构造方法是私有的,不可以直接创建,但可以通过Pattern.complie(String regex)简单工厂方法创建一个正则表达式
        Pattern p = Pattern.compile("\\w+");//将给定的正则表达式编译到模式中
        System.out.println(p.pattern());//返回 \w+
    }

    @Test
    public void test31(){
        //Pattern有一个split(CharSequence input)方法,用于分隔字符串,并返回一个String[],
        Pattern p = Pattern.compile("\\d+");
        String[] str = p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        System.out.println(Arrays.toString(str));//[我的QQ是:, 我的电话是:, 我的邮箱是:aaa@aaa.com]
    }
    @Test
    public void test32(){
        //Pattern.matcher(String regex,CharSequence input)是一个静态方法,用于快速匹配字符串,该方法适合用于只匹配一次,且匹配全部字符串
        Pattern.matches("\\d+","2223");//返回true
        Pattern.matches("\\d+","2223aa");//返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到
        Pattern.matches("\\d+","22bb23");//返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到
    }
    @Test
    public void test33(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        System.out.println(m.pattern());//返回p 也就是返回该Matcher对象是由哪个Pattern对象的创建的
    }
    @Test
    public void test34(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        System.out.println(m.matches());//返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功.
        Matcher m2=p.matcher("2223");
        System.out.println(m2.matches());//返回true,因为\d+匹配到了整个字符串
    }
    @Test
    public void test35(){
        //lookingAt()对前面的字符串进行匹配,只有匹配到的字符串在最前面才返回true
        Pattern p=Pattern.compile("\\d+");
        Matcher m=p.matcher("22bb23");
        System.out.println(m.lookingAt());//返回true,因为\d+匹配到了前面的22
        Matcher m2=p.matcher("aa2223");
        System.out.println(m2.lookingAt());//返回false,因为\d+不能匹配前面的aa
    }

    @Test
    public void test36() {
        //find()对字符串进行匹配,匹配到的字符串可以在任何位置.
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        System.out.println(m.find());//返回true
        Matcher m2 = p.matcher("aa2223");
        System.out.println(m2.find());//返回true
        Matcher m3 = p.matcher("aa2223bb");
        System.out.println(m3.find());//返回true
        Matcher m4 = p.matcher("aabb");
        System.out.println(m4.find());//返回false
    }

    @Test
    public void test37(){
        Pattern p=Pattern.compile("\\d+");
        Matcher m=p.matcher("aaa2223bb");
        m.find();//匹配2223
        m.start();//返回3
        m.end();//返回7,返回的是2223后的索引号
        m.group();//返回2223

        Matcher m2 = p.matcher("2223bb");
        m.lookingAt();   //匹配2223
        m.start();   //返回0,由于lookingAt()只能匹配前面的字符串,所以当使用lookingAt()匹配时,start()方法总是返回0
        m.end();   //返回4
        m.group();   //返回2223

        Matcher m3=p.matcher("2223bb");
        m.matches();   //匹配整个字符串
        m.start();   //返回0,原因相信大家也清楚了
        m.end();   //返回6,原因相信大家也清楚了,因为matches()需要匹配所有字符串
        m.group();   //返回2223bb
    }

    @Test
    public void test38() {
        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m = p.matcher("aaa2223bb");
        m.find();   //匹配aaa2223
        m.groupCount();   //返回2,因为有2组
        m.start(1);   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
        m.start(2);   //返回3
        m.end(1);   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
        m.end(2);   //返回7
        m.group(1);   //返回aaa,返回第一组匹配到的子字符串
        m.group(2);   //返回2223,返回第二组匹配到的子字符串
    }

    @Test
    public void test39() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com");
        while (m.find()) {
            System.out.println(m.group());
        }
    }


}


