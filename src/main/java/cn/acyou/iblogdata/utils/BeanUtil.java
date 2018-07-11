package cn.acyou.iblogdata.utils;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    /**
     * 将Bean转换为Map（不包含null值）
     * @param obj bean
     * @return HashMap
     */
    public static HashMap<String, Object> convertToMap(Object obj) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            if (varName.equals("serialVersionUID")) {
                continue;
            }
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);
            Object o = null;
            try {
                o = fields[i].get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (o != null)
                map.put(varName, o.toString());
            fields[i].setAccessible(accessFlag);
        }
        return map;
    }


    /**
     * 将一个 JavaBean 对象转化为一个  Map（包含null值）
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, null);
                }
            }
        }
        return returnMap;
    }




    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param clazz 对象所属类
     * @param map Map
     */
    @SuppressWarnings("rawtypes")
    public static <T> T  convertMapToBean(Class<T> clazz, Map map) {
        String jsonStr = JSON.toJSONString(map);
        return JSON.parseObject(jsonStr, clazz);
    }


}
