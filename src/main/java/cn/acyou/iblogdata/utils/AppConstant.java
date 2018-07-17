package cn.acyou.iblogdata.utils;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:42
 **/
public class AppConstant {

    public static final int SUCCESS = 200;
    public static final String MESSAGE = "请求成功！";


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





}
