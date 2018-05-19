package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.BeanUtil;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.script.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        String str = "youfang";
        String ss = DigestUtils.sha1DigestAsHex(str);
        System.out.println(ss);
    }
}
