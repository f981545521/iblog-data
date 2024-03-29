package cn.acyou.iblogdata.test.other;

import cn.acyou.iblogdata.constant.AppConstant;
import cn.acyou.iblogdata.utils.ParamUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Map;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-27 上午 11:21]
 **/
public class MainTest implements Cloneable{
    private static Logger logger = null;

    public MainTest() {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("调用构造函数");
    }


    public static void main(String[] args) throws Exception{
        //new MainTest();//1. 通过new 关键字
        //Class clazz = Class.forName("cn.acyou.iblogdata.test.other.MainTest");
        //clazz.newInstance();// 2.1 通过反射newInstance()
        //MainTest.class.newInstance();// 2.2 通过反射newInstance()

        //MainTest test = new MainTest();
        //Object obj = test.clone();// 3. 通过clone方法获取对象
        //System.out.println(obj);

        //Constructor<MainTest> constructor = MainTest.class.getConstructor();//2. 通过Constructor 获取构造器来实例化
        //constructor.newInstance();

        System.out.println("开始");
        logger.debug("哈哈哈哈！");
    }

    @Test
    public void test2(){
        String s = AppConstant.BucketName.BUCKET_IMAGE;
        System.out.println(s);
    }

    @Test
    public void test3(){
        Double d1 = 0.0;
        DecimalFormat dFormat = new DecimalFormat("#.00");
        dFormat.format(d1);
        System.out.println(d1);
        System.out.println();
    }

    @Test
    public void test4(){
        String wxCall = "http://acyou.cn/api/wx/verification";
        System.out.println(wxCall.contains("/wx"));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("q");
        stringBuffer.append("w");
        stringBuffer.append("e");
        stringBuffer.append("r");
        String sb = stringBuffer.toString();
        System.out.println(sb.contains("r"));
    }


    @Test
    public void test5(){
        if ( "a".equals(getStr())){
            System.out.println(getStr());
        }
    }
    @Test
    public void test5_1(){
        String s = getStr();
        if ( "a".equals(s)){
            System.out.println(s);
        }
    }

    private String getStr(){
        System.out.println("执行方法getStr");
        return "a";
    }

    @Test
    public void testmd5(){
        String code = "MHBQI9";
        String base64Code = "TUhCUUk5";
        System.out.println(DigestUtils.md5Hex(code));
        byte[] rest = Base64.getDecoder().decode(base64Code);
        String restStr = new String(rest);
        System.out.println(restStr);
    }

    @Test
    public void test6() throws UnsupportedEncodingException {
        String result = URLEncoder.encode("DFVG43", "utf-8");
        System.out.println(result);
    }

    @Test
    public void test7(){
        String url = "http://localhost:8080/index?inviteCode=MHBQI9";
        String url2 = "http://localhost:8080/index";
        Map<String, String> map = ParamUtil.getQueryString(url);
        System.out.println(map);
    }



}
