package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.script.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class SimpleTest {
    @Test
    public void test21(){
        Student student = new Student();
        Student student2 = new Student();
        student.setId(1223);
        student.setName("张而非");
        student.setAge(null);
        Map<String, Object> map = BeanUtil.convertToMap(student);
        BeanUtils.copyProperties(student, student2);
        System.out.println(student2);
    }

    /**
     * 转义<></> 防止XSS
     */
    @Test
    public void test22(){
        String ss = "<script>alert(1)</script>";
        String ss2 = "safsfafsfa";
        String aa = ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        System.out.println(aa);
        System.out.println(ss.replaceAll("<", "&lt;"));
        System.out.println(ss.replaceAll(">", "&gt;"));
    }
    @Test
    public void test222() throws UnsupportedEncodingException {
        String wxUrl = "http://www.qq.com/api/wx/verification";
        String encodeURL = URLEncoder.encode(wxUrl, "UTF-8");
        System.out.println(encodeURL);
    }

    @Test
    public void testSHA1(){

        String signature = "8a5b60eb577869ec8d33af0676461a527c57e28d";
        String timestamp = "1526698659";
        String nonce =  "1156985779";
        String echostr =  "13059544329142392751";
        String token =  "youfang";

        ArrayList<String> list = new ArrayList<>();
        list.add(nonce);
        list.add(timestamp);
        list.add(token);
        //字典序排序
        Collections.sort(list);
        System.out.println(list.get(0) + list.get(1) + list.get(2));
        String encodStr = DigestUtils.sha1DigestAsHex(list.get(0) + list.get(1) + list.get(2));
        System.out.println(encodStr);
        System.out.println(signature);
    }
}
