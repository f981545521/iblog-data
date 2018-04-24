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
}
