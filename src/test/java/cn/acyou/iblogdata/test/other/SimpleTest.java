package cn.acyou.iblogdata.test.other;

import cn.acyou.iblog.entity.Student;
import cn.acyou.iblog.entity.Teacher;
import cn.acyou.iblog.utility.DateUtil;
import cn.acyou.iblog.utility.MathUtil;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.test.entity.Animal;
import cn.acyou.iblogdata.constant.AppConstant;
import cn.acyou.iblogdata.utils.JsonUtil;
import cn.acyou.iblogdata.utils.RandomUtil;
import cn.acyou.iblogdata.vo.StudentLogTestVo;
import cn.acyou.iblogdata.vo.StudentVo;
import com.google.common.collect.Maps;
import com.google.common.primitives.Doubles;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math.util.MathUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.print.PrintService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-06 下午 01:49]
 **/
public class SimpleTest {

    @Test
    public void test1(){
        Integer i1 = 1234;
        System.out.println(i1.toString());

        Double d1 = 213D;
        System.out.println(Math.round(d1));
    }

    @Test
    public void test2(){
        String ss = "aaa";
        ss += 1234;
        System.out.println(ss);
    }

    @Test
    public void test3(){
        String audioname = "2132142325435.amr";
        audioname = audioname.substring(0, audioname.lastIndexOf(".")) + ".mp4";
        System.out.println(audioname);
    }

    @Test
    public void test4(){
        System.out.println(100/0);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        try {
            if (n == 2){
                throw new ServiceException();
            }
        }catch (Exception e){
            System.out.println("exception was happend");
            throw new RuntimeException();
        }
        System.out.println("运行结束");
    }

    @Test
    public void randomValue(){
        String[] rooms = new String[]{"123","345","456","3245"};
        for (int i=0;i<100;i++){
            System.out.println(rooms[new Random().nextInt(rooms.length)]);
        }
    }

    @Test
    public void test23s(){
        String[] rooms = new String[]{"123","345","456","3245"};
        Random random = new Random();
        System.out.println(rooms[random.nextInt(rooms.length)]);
        System.out.println(random.nextInt(rooms.length));
    }

    @Test
    public void test3445(){
        int i = 100;
        int j = 102;
        int k = i - j;
        System.out.println(k<0?0:k);
    }

    @Test
    public void test222(){
        double d1 = 3455.5;
        int i1 = new Double(d1).intValue();
        System.out.println(i1);
    }

    @Test
    public void test21(){
        StudentLogTestVo logTestVo = new StudentLogTestVo();
        logTestVo.setName("等等");
        logTestVo.setAge(12);
        Object[] obj = new Object[]{logTestVo};
        System.out.println(obj[0]);
    }

    @Test
    public void test31(){
        int i =1/0;
        System.out.println(i);
    }

    @Test
    public void testw34(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("12", "1230");
        stringMap.put("13", "1230");
        stringMap.put("14", "1230");
        stringMap.put("15", "1230");
        stringMap = new HashMap<>();
        System.out.println(stringMap);
    }

    @Test
    public void test44(){
        StudentVo studentVo = new StudentVo();
        if (studentVo != null && studentVo.getTeacherList() != null && studentVo.getTeacherList().get(1) != null){
            System.out.println(studentVo.getTeacherList().get(1));
        }
        Optional.of(studentVo).map(StudentVo::getTeacherList).ifPresent(System.out::println);

    }
    @Test
    public void test51(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        studentVo.setName("ass");
        System.out.println(studentVo);
        Optional<List<Teacher>> teachers = Optional.of(studentVo).map(StudentVo::getTeacherList);
        System.out.println(studentVo.getTeacherList().size());
    }
    @Test
    public void test52(){
        //调用工厂方法创建Optional实例
        Optional empty = Optional.ofNullable(null);
    }


    @Test
    public void test54(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        studentVo.setName("ass");
        System.out.println(studentVo);
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getAge).orElse(1);
        System.out.println(studentVo);
        System.out.println(studentVo.getTeacherList().size());
    }
    @Test
    public void test55(){
        //调用工厂方法创建Optional实例
        StudentVo studentVo = new StudentVo();
        Assert.notNull(studentVo.getName(), "不能为空");
    }
    @Test
    public void test56(){
        StudentVo studentVo = new StudentVo();
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        Optional<String> nameOpt = stuOpt.map(StudentVo::getName);
        String a = nameOpt.orElse("默认你是小明");
        // nameOpt.orElseThrow(() -> new ServiceException("不能为空"));
        System.out.println("好的");
        System.out.println(studentVo);
        Optional.of(studentVo).orElseThrow(() -> new ServiceException("不能为空"));
        //Optional.of(studentVo.getName()).orElseThrow(() -> new ServiceException("不能为空"));
    }

    @Test
    public void test57(){
        StudentVo studentVo = new StudentVo();
        studentVo.setName("小花");
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getName).ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("--------------");
                s = "哈里斯";
                System.out.println(s);
            }
        });
        //stuOpt.map(StudentVo::getTeacherList).orElseThrow(() -> new ServiceException("不能为空"));
        System.out.println(studentVo);
    }
    @Test
    public void test58(){
        StudentVo studentVo = new StudentVo();
        Optional<StudentVo> stuOpt = Optional.of(studentVo);
        stuOpt.map(StudentVo::getName);
        System.out.println(studentVo);
    }

    @Test
    public void test588(){
        String path = SystemUtils.getUserHome().getPath();
        System.out.println(path);
    }

    @Test
    public void test61(){
        Logger logger = Logger.getLogger("ad");
        logger.info("好的");
    }

    @Test
    public void test62(){
        int sum = 0;
        for (int j=0;j<=100;j++){
            sum+=j;
        }
        System.out.println(sum);
    }

    @Test
    public void test63(){
        Integer i = 128;
        Integer b = 128;
        System.out.println(i == 128);//true
        System.out.println(i == b);//false
        System.out.println(i.equals(128));//true
    }

    @Test
    public void testDistance(){
        Integer dis = 3456;
        Double d = Math.round(dis/100d)/10d;
        System.out.println(d);


        DecimalFormat df = new DecimalFormat("######0.00");
        String s = df.format(Double.parseDouble(String.valueOf(dis)));
        System.out.println(s);
    }


    @Test
    public void testEncoder() throws IOException {
        String waitStr = "youfang";
        System.out.println(DigestUtils.md5Hex(waitStr));//MD5加密
        System.out.println(DigestUtils.sha1Hex(waitStr));//sha1加密
        //BASE64加密与解密
        String baseCode = (new BASE64Encoder()).encodeBuffer(waitStr.getBytes());
        System.out.println(baseCode);
        System.out.println(new String((new BASE64Decoder()).decodeBuffer(baseCode)));

    }

    @Test
    public void test66(){
        double d1 = 0.1;
        double d2 = 0.1;
        System.out.println(d1 + d2);
    }

    @Test
    public void test67(){
        String jsonStr = "{\"packageId\":6}";
        String jsonStr2 = "{&quot;packageId&quot;:12}";
        System.out.println(JsonUtil.readTree(jsonStr, "packageId"));
    }
    @Test
    public void test68(){
        String jsonStr = "packageId-123+dfsf-3fds+";
        String[] s = jsonStr.split("\\+");
        Map<String, String> result = new HashMap<>();
        for (String ss:s){
            if (ss.indexOf("-") > 0){
                String key = ss.substring(0, ss.indexOf("-"));
                String val = ss.substring(ss.indexOf("-") + 1);
                System.out.println(key + ":" + val);
            }
        }
    }

    @Test
    public void test69(){
        String ss = "天天健身-购卡";
        System.out.println(ss);
    }

    @Test
    public void test70(){
        Double fee = 0.21;
        System.out.println(wxFee(fee));
        System.out.println((fee * 100d));
    }

    public static String wxFee(double money) {
        Double f = money * 100d;
        return f.intValue() + "";
    }

    @Test
    public void test123(){
        String birth = "1992 13 31";
        DateUtil.parseDate(birth, "yyyy MM dd");
        System.out.println(birth.matches("\\d[4] \\d[2] \\d[2]"));
    }

    @Test
    public void test124(){
        BigDecimal decimalA = new BigDecimal(12.00);
        BigDecimal decimalResult = decimalA.pow(2);
        System.out.println(decimalResult);
    }

    @Test
    public void test125(){
        String no = "PL201811211759224482505";
        String randomStr = RandomUtil.createRandom(true, 5);
        String noBak = no + "_" + randomStr;
        System.out.println(noBak);
        if (no.lastIndexOf("_") > 1){
            System.out.println(no.substring(0, no.lastIndexOf("_")));
        }
        System.out.println(noBak.substring(0, noBak.lastIndexOf("_")));
    }

    @Test
    public void testToString(){
        cn.acyou.iblogdata.entity.Teacher teacher = new cn.acyou.iblogdata.entity.Teacher();
        teacher.setName("xxx");
        System.out.println(teacher);
    }

    @Test
    public void testIntegerEquals(){
        Integer type = 2111;
        System.out.println(new Integer(2111).equals(type));
        System.out.println(211 == type);
    }

    @Test
    public void testBigDecimal(){
        Double d1 = 1.1;
        System.out.println(new BigDecimal(d1));
    }

    @Test
    public void test34(){
        String url = "/1mkt/bargain/BA201812270950471538258";
        System.out.println(url.indexOf("/mkt/bargain/"));
        System.out.println(url.contains("/mkt/bargain/"));
        System.out.println(url.contains("/1mkt/bargain/"));
    }

    @Test
    public void testBoolean(){
        Animal animal = new Animal();
        System.out.println(animal.isHut());
    }

    @Test
    public void testMinutes(){
        DateTime currentDate = new DateTime();
        DateTime tenBefore = currentDate.plusMinutes(10);
        System.out.println(tenBefore.toString(AppConstant.SPECIFIC_DATE_FORMAT_PATTERN));
        int diffMinutes = Minutes.minutesBetween(tenBefore, currentDate).getMinutes();//前小 后大
        System.out.println(diffMinutes);
    }
    @Test
    public void testMinutes2(){
        Date currentDate1 = DateUtil.parseDate("2018-12-27 14:31:41", AppConstant.SPECIFIC_DATE_FORMAT_PATTERN);
        DateTime currentDate = new DateTime(currentDate1);//指定日期
        DateTime tenBefore = currentDate.plusMinutes(10);//10分钟后
        System.out.println(currentDate.toString(AppConstant.SPECIFIC_DATE_FORMAT_PATTERN));
        System.out.println(tenBefore.toString(AppConstant.SPECIFIC_DATE_FORMAT_PATTERN));
        if (tenBefore.isAfterNow()){//在现在前
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

    /**
     * 　Integer 的源码中，对传入参数i做了一个if判断。在-128<=i<=127的时候是直接用的int原始数据类型，
     * 而超出了这个范围则是new了一个对象。我们知道 == 符号在比较对象的时候是比较的内存地址，而对于原始数据类型是直接比对的数据值。
     */
    @Test
    public void testInteger(){
        int a = 1000;
        int b = 1000;
        Integer wa = a;
        Integer wb = 1000;
        System.out.println(wa.equals(wb));//true
        System.out.println(wa==wb);//false
        System.out.println(a == wb);
    }

    @Test
    public void testInteger2(){
        int a = 1000;
        Integer b = 1000;
        Integer bb = 1000;
        System.out.println(a == b);
        System.out.println(a == bb);
        System.out.println(b == bb);
        System.out.println(b.equals(a));

    }

    @Test
    public void testInteger3(){
        int a = 129;
        Integer b = 129;
        Student student = new Student();
        System.out.println(student.getAge());
        System.out.println(b.equals(student.getAge()));
        System.out.println(a == student.getAge());
    }
    @Test
    public void testInteger4(){
        int a = 129;
        Integer b = 129;
        Student student = new Student();
        student.setAge(129);
        System.out.println(student.getAge());
        System.out.println(b.equals(student.getAge()));
        System.out.println(a == student.getAge());//true
        System.out.println(b == student.getAge());//false
    }

    @Test
    public void testfgd(){
        BigDecimal b1 = new BigDecimal(String.valueOf("0.00"));
        System.out.println(b1.compareTo(BigDecimal.ZERO) == 0);
    }


    @Test
    public void testddgf(){
        String code = "8766225";
        System.out.println(code.length());
        code = String.format("%0" + 8 + "d", Integer.parseInt(code));
        System.out.println(code);

    }

    private static final BigDecimal PERCENT_HUNDRED = BigDecimal.valueOf(100);

    @Test
    public void testPrecent(){
        BigDecimal bigDecimal = new BigDecimal(655);
        BigDecimal price = new BigDecimal(365);
        BigDecimal smallMun = bigDecimal.divide(PERCENT_HUNDRED,2,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(price.multiply(smallMun, MathContext.UNLIMITED));
    }
    @Test
    public void testPrecent2(){
        BigDecimal bigDecimal = new BigDecimal("0.32");
        BigDecimal price = new BigDecimal("0.33");
        System.out.println(price.multiply(bigDecimal));
        System.out.println(price.multiply(bigDecimal, MathContext.DECIMAL32));
        System.out.println(price.multiply(bigDecimal, MathContext.DECIMAL64));
        System.out.println(price.multiply(bigDecimal, MathContext.DECIMAL128));
    }

    @Test
    public void test234(){
        int code = 23;
        System.out.println(String.format("%06d", code));
    }

    @Test
    public void test245(){
        String s = null;
        s.isEmpty();
        System.out.println(StringUtils.isEmpty(s));
    }

    @Test
    public void test333(){
        Map<Integer, List<String>> listMap = Maps.newHashMap();
        List<String> stringList = new ArrayList<>();
        stringList.add("111");
        stringList.add("222");
        listMap.put(1, stringList);
        System.out.println(listMap);

        List<String> strings = listMap.get(1);
        strings.add("333");
        System.out.println(listMap);
    }

    @Test
    public void test5464(){
        System.out.println(0%4==0);
        for (int i=0;i<20;i++){
            System.out.println((i%4==0) + "," + ((i+1)%4==0));
        }
    }

    @Test
    public void test435345(){
        List<String> stringList = Lists.newArrayList();
        stringList.add("ss");
        System.out.println(stringList);

    }

    @Test
    public void test3242(){
        System.out.println(RandomUtil.createRandomStr(5) + ".png");
        String[] as = new String[5];
        as[1] = "2";
        System.out.println(as.length);
    }

    @Test
    public void testre(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("颜色", "红色");
        map.put("内存", "4G");
        System.out.println(map.get(0));
    }

    @Test
    public void test2356(){
        String hql = " select o from MbTemplateVariables o where 1=1";
        hql += " and o.bllx = " + 2;
        System.out.println(hql);
    }

    @Test
    public void testfgdg(){
        DateTime cur = new DateTime();
        for (int i=0; i<365; i++){
            System.out.println( cur.toString("yyyy/MM/dd"));
            cur = cur.minusDays(1);
        }
    }

    @Test
    public void testsfgdg(){
        String s = "2018/04/23";
        System.out.println(s.replaceAll("/", ""));
    }

    @Test
    public void testConcatPerformance(){
        cn.acyou.iblogdata.entity.Student student = new cn.acyou.iblogdata.entity.Student();
        student.setName("小王");
        student.setAge(22);
        long start = System.currentTimeMillis();
        String  hql = "update MbTemplateContent set " +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjmc = '" + student.getName() + "'" +
                " wjlj = " + student.getName() +
                " wjnr = " + student.getAge() +
                " xzblId = " + student.getName() +
                " where mbId = " + student.getName();
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));

    }

    @Test
    public void ttst3(){
        Double d = 111.0;
        System.out.println(String.format("%02d", d.intValue()));
    }

    @Test
    public void tstt34() {
        for (int i = 0; i < 50; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

    @Test
    public void teest324(){
        System.out.println("SFGZ_YWBL_DOSSIER_EVIDENCE_DET".length());
    }

    @Test
    public void tesrtsrt(){
        Map map = new HashMap();
        System.out.println(map instanceof Collection);
    }

    @Test
    public void trstttt(){
        List<String> stringList = new ArrayList<>();
        stringList.add("111");
        stringList.add("222");
        stringList.add("333");
        System.out.println(StringUtils.join(stringList, ","));
    }

    @Test
    public void testsrt(){
        String a = "01";
        String b = "02";
        String c = "02";
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
        System.out.println(b.compareTo(c));
    }
    @Test
    public void testsr2t(){
        String a = "01";
        String b = "02";
        String s = StringUtils.isNotEmpty(a) ? "33":"23";
        System.out.println(DigestUtils.md5Hex("1"));
    }
    @Test
    public void testsr22t(){
        String a = "01,";
        if (a.endsWith(",")){
            System.out.println(a.substring(0, a.lastIndexOf(",")));
        }
        System.out.println(StringUtils.stripEnd(a, ","));
        System.out.println(StringUtils.trim(","));
        String[] strings = new String[]{"aaa","bbb"};
        System.out.println(StringUtils.join(strings, ","));
        String b = ",xxx,xxxs,";
        String c = StringUtils.strip(b, ",");
        System.out.println(b);
        System.out.println(c);
    }

    @Test
    public void testCharacter(){
        System.out.println(CharEncoding.UTF_8);
    }
    @Test
    public void testCddd(){
        long id = 10;
        DecimalFormat df = new DecimalFormat("0000");
        System.out.println(df.format(id));
    }
    @Test
    public void ffg(){
        System.out.println(9999>9999);
    }
    @Test
    public void test3ww(){
        Integer a = 2235;
        System.out.println(String.valueOf(a));
        String b = "a23";
        System.out.println(Integer.valueOf(b));
        System.out.println(NumberUtils.isDigits("2.3F"));
        System.out.println(NumberUtils.toInt(b, 2));
        Double d = 2.33;
    }

    @Test
    public void testsrt2(){
        String s = "09,10,08,03,05,02,06,07,11,12,14";
        String[] s_ = s.split(",");
        Arrays.sort(s_);
        System.out.println(Arrays.toString(s_));
    }

    @Test
    public void testsrt3(){
        String s = "09,10,08,03,05,02,06,07,11,12,14";
        String[] s_ = s.split(",");
        List<String> stringList = Arrays.asList(s_);
        Collections.sort(stringList);
        System.out.println(stringList);
    }


    @Test
    public void testsrt333(){
        String s = "5.66";
        System.out.println(NumberUtils.isNumber(s));
        Double aDouble = 5.555;
        System.out.println(MathUtil.roundHalfUp(aDouble, 3));
        System.out.println(MathUtils.round(aDouble,2,BigDecimal.ROUND_HALF_UP));
        BigDecimal decimal = new BigDecimal(aDouble);
        System.out.println(decimal.setScale(2, RoundingMode.HALF_UP).doubleValue());
        System.out.println(aDouble);
    }

    @Test
    public void dfdsfs(){
        String[] ass = new String[3];
        ass[0] = "222";
        ass[1] = "333";
        ass[2] = "444";
        System.out.println(StringUtils.join(ass, ","));
        List<String> stringList = new ArrayList<>();
        stringList.add("111");
        stringList.add("222");
        stringList.add("333");
        System.out.println(StringUtils.join(stringList, ","));
    }

    @Test
    public void tetteet(){
        Set<String> aSet = Sets.newSet("2","3","4","5");
        Set<String> bSet = Sets.newSet("3","4","5","6","7");
        bSet.removeAll(aSet);
        System.out.println(bSet);
    }

    @Test
    public void tettee2t(){
        String a = "01";
        String b = "02";
        System.out.println(a.compareTo(b));
    }
    @Test
    public void tettee23t(){
        String a = "1";
        Integer b = 1;
        System.out.println(a);
    }
    @Test
    public void tettee2322t(){
        String a = "2.1";
        System.out.println(Double.valueOf(a).intValue());

    }

}
