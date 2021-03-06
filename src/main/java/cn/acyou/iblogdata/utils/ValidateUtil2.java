package cn.acyou.iblogdata.utils;

import cn.acyou.iblogdata.annotation.valid.BaseValid;
import cn.acyou.iblogdata.annotation.valid.RegexType;
import cn.acyou.iblogdata.exception.ServiceException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 请求参数验证工具类
 *
 * @author youfang
 * @version [1.0.0, 2017年12月11日]
 */
public class ValidateUtil2 {
    private static final Logger logger = LoggerFactory.getLogger(ValidateUtil2.class);

    /**
     * 验证解析
     *
     * @param object object
     * @return result
     */
    public static void valid(Object object) {
        //获取object的类型
        Class<?> clazz = object.getClass();
        //获取该类型声明的成员
        Field[] fields = clazz.getDeclaredFields();
        //遍历属性
        for (Field field : fields) {
            //对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            validate(field, object);
            //重新设置会私有权限
            field.setAccessible(false);
        }

    }

    /**
     * 验证注解
     *
     * @param field 字段
     * @param object 值
     * @return result
     */
    public static void validate(Field field, Object object) {
        Object value = null;
        String description;
        //获取对象的成员的注解信息
        BaseValid baseValid = field.getAnnotation(BaseValid.class);
        //未加注解的字段直接返回
        if (baseValid == null) {
            return;
        }
        //获取值
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        String fieIdName = field.getName();
        description = baseValid.message();
        /* *********** 注解解析工作开始 ***************** */
        if (baseValid.notNull()) {
            if (null == value || StringUtils.isBlank(value.toString())) {
                description = "".equals(description) ? "参数错误" : description;
                throw new ServiceException(description);
            }
        }

        //添加该条件：为了适应请求参数的一种情况，可以为空,但是如果不为空的情况下的判断
        if (null != value && StringUtils.isNotBlank(value.toString())) {
            if (value.toString().length() > baseValid.maxLength() && baseValid.maxLength() != 0) {
                logger.warn("[数据校验]|{}|{}|{}|{}", "valid failed", fieIdName , "长度超过了" , baseValid.maxLength());
                description = "".equals(description) ? "参数错误" : description;
                throw new ServiceException(description);
            }

            if (value.toString().length() < baseValid.minLength() && baseValid.minLength() != 0) {
                logger.warn("[数据校验]|{}|{}|{}|{}", "valid failed", fieIdName , "长度小于了" , baseValid.minLength());
                description = "".equals(description) ? "参数错误" : description;
                throw new ServiceException(description);
            }

            if (NumberUtils.isNumber(value.toString()) && baseValid.min() != 0
                    && Integer.valueOf(value.toString()) < baseValid.min()) {
                logger.warn("[数据校验]|{}|{}|{}|{}", "valid failed", fieIdName , "不能小于" , baseValid.min());
                description = "".equals(description) ? "参数错误" : description;
                throw new ServiceException(description);
            }

            if (NumberUtils.isNumber(value.toString()) && baseValid.max() != 0
                    && Integer.valueOf(value.toString()) > baseValid.max()) {
                logger.warn("[数据校验]|{}|{}|{}|{}", "valid failed", fieIdName , "不能大于" , baseValid.max());
                description = "".equals(description) ? "参数错误" : description;
                throw new ServiceException(description);            }

            if (baseValid.notInRange().length > 0) {
                if (!ArrayUtils.contains(baseValid.notInRange(), value.toString())) {
                    logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "取值不在范围内");
                    description = "".equals(description) ? "参数错误" : description;
                    throw new ServiceException(description);
                }
            }

            if (baseValid.numberNotInRange().length > 0) {
                if (!ArrayUtils.contains(baseValid.numberNotInRange(), Integer.parseInt(value.toString()))) {
                    logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "取值不在范围内");
                    description = "".equals(description) ? "参数错误" : description;
                    throw new ServiceException(description);
                }
            }
            if (baseValid.notEmpty()) {
                if (value instanceof Collection &&  ((Collection) value).isEmpty()){
                    logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "集合为空");
                    description = "".equals(description) ? "参数错误" : description;
                    throw new ServiceException(description);
                }
                if (value instanceof Map &&  ((Map) value).isEmpty()){
                    logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "集合为空");
                    description = "".equals(description) ? "参数错误" : description;
                    throw new ServiceException(description);
                }
            }

            if (baseValid.regexType() != RegexType.NONE) {
                String result = null;
                switch (baseValid.regexType()) {
                    case NONE:
                        break;
                    case SPECIALCHAR:
                        if (RegexUtils.hasSpecialChar(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "不能含有特殊字符");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case CHINESE:
                        if (RegexUtils.isChinese2(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "不能含有中文字符");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case EMAIL:
                        if (!RegexUtils.isEmail(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "邮箱地址格式不正确");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case IP:
                        if (!RegexUtils.isIp(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "IP地址格式不正确");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case NUMBER:
                        if (!RegexUtils.isNumber(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "不是数字");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case PHONENUMBER:
                        if (!RegexUtils.isPhoneNumber(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "不是手机号码");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case DATE:
                        if (!RegexUtils.isDateStr(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "日期格式出错");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    case DATETIME:
                        if (!RegexUtils.isDateTime(value.toString())) {
                            logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "时间格式出错");
                            description = description.equals("") ? "参数错误" : description;
                            result = description;
                        }
                        break;
                    default:
                        break;
                }
                if (null != result) {
                    throw new ServiceException(result);
                }
            }

            if (!"".equals(baseValid.regexExpression())) {
                if (value.toString().matches(baseValid.regexExpression())) {
                    logger.warn("[数据校验]|{}|{}|{}", "valid failed", fieIdName , "格式不正确");
                    description = "".equals(description) ? "格式不正确" : description;
                    throw new ServiceException(description);
                }
            }
        }
        /* ***********注解解析工作结束***************** */
    }


}
