package cn.acyou.iblogdata.constant;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.base.CharMatcher;

import java.lang.reflect.Field;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:42
 **/
public class AppConstant {

    public static final int SUCCESS = 200;
    public static final String MESSAGE = "请求成功！";

    public static final String MQ_CHANNEL_MAIN = "hello";

    public static final String SHORT_DATE_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String SPECIFIC_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SERIES_FORMAT_PATTERN = "yyyyMMddHHmmss";


    public static final String BASE_PACKAGE = "cn.acyou.iblogdata";
    //Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";
    //Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    //Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    //ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    //Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    public static final String TEST_PACKAGE = BASE_PACKAGE + ".test";

    //Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".dao.Mapper";

    public static final String UNDER_LINE = "_";


    public static final String COMMA = ",";


    /**
     * 微信授权URL
     */
    public final static String WX_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * 通过code换取网页授权access_token
     */
    public final static String WX_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 根据APPID & appsecret 获取accesstoken
     */
    public final static String WX_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    public final static String WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 获取用户基本信息(UnionID机制)
     */
    public final static String WX_USER_INFO2 = "https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * 验证access_token
     */
    public final static String WX_AUTH = "https://api.weixin.qq.com/sns/auth";

    public class BucketName{
        public final static String BUCKET_IMAGE = "iblog-image";
        public final static String BUCKET_VIDEO = "iblog-video";
        public final static String BUCKET_AUDIO = "iblog-video";

    }

    public static void main(String[] args) throws Exception{
        Class<?> clazz = WDLX.class;
        Field[] declaredFields = ReflectUtil.getFields(clazz);
        for (Field field: declaredFields){
            System.out.println("allWdlxRespList.add(new AllWdlxResp(WDLX."+field.getName()+", getWDName(WDLX."+field.getName()+")));");
            //String name = field.getName();
            //System.out.println(name);
            //Object o = field.get(clazz);
            //System.out.println(o);
        }
    }


    public static void main4(String[] args) {
        System.out.println("ok");
        Class<?> clasz = GZLB.class;
        printInnerParamValue(clasz);
    }

    public static void printInnerParamValue(Class<?> clasz){
            Field[] fields = clasz.getDeclaredFields();
            for(Field field : fields){
                try {
                    Object object = field.get(clasz);
                    System.out.println("获取到的feild, name=" + field.getName()+",   value="+ object.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

    }

    public static class GZLB{
        /* 国内民事 */
        public static final String GNMS = "01";
        /* 国内经济 */
        public static final String GNJJ = "02";
        /* 涉外民事 */
        public static final String SWMS = "03";
        /* 涉外经济 */
        public static final String SWJJ = "04";
        /* 涉港 */
        public static final String SG = "05";
        /* 涉澳 */
        public static final String SA = "06";
        /* 涉台 */
        public static final String ST = "07";
        /* 其他 */
        public static final String QT = "99";

        //获取报表分类
        public static final String getReportName(String code){
            if (GNMS.equals(code) || GNJJ.equals(code)){
                return "国内公证";
            }
            if (SWMS.equals(code) || SWJJ.equals(code)){
                return "涉外公证";
            }
            if (SG.equals(code) || SA.equals(code)){
                return "涉港澳公证";
            }
            if (ST.equals(code)){
                return "涉台公证";
            }
            return null;
        }

    }
    /**
     * 文档类型
     */
    public static class WDLX{
        /* 申请表 */
        public static final String SQB = "1";
        /* 受理通知书 */
        public static final String SLTZS = "2";
        /* 不予受理通知书 */
        public static final String BYSLTZS = "3";
        /* 告知书 */
        public static final String GZS = "4";
        /* 缴费清单 */
        public static final String JFQD = "5";
        /* 终止公证决定书 */
        public static final String ZZGZJDS = "7";
        /* 回执单 */
        public static final String HZD = "8";
        /* 档案封面 */
        public static final String DAFM = "9";
        /* 呈批表 */
        public static final String CPB = "10";
        /* 笔录 */
        public static final String BL = "30";
        /* 代书 */
        public static final String DS = "40";
        /* 公证书 */
        public static final String GONG_ZS = "50";
        /* 卷宗目录 */
        public static final String JZML = "61";

        /* 代书（公证事务） */
        public static final String DS_GZSW = "71";
        /* 咨询 */
        public static final String ZX = "72";
        /* 法律意见书 */
        public static final String FLYJS = "73";
        /* 司法辅助 */
        public static final String SFFZ = "74";

        /* 不予办理通知书 */
        public static final String BYBLTZS = "123";

        /**
         * 允许多文档存在
         * @param wdlx
         * @return
         */
        public static boolean allowMultipart(String wdlx){
            return BL.equals(wdlx) || DS.equals(wdlx) || GONG_ZS.equals(wdlx);
        }

    }

    /**
     * 过滤掉31以下的ascii码以及双引号和反斜扛
     */
    public static final CharMatcher DANGER_CHAR_MATCHER = CharMatcher.inRange('\0', '\u001F')
            .or(CharMatcher.is('"'))
            .or(CharMatcher.is('\\'));



}
