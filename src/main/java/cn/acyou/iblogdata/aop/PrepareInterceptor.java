package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author youfang
 * @version [1.0.0, 2020/3/19]
 **/

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class PrepareInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        // sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            // 插入操作
            if (object instanceof DefaultSqlSession.StrictMap) {
                // FIXME 批量操作（还有没有别的方式判断？）
                Map map = (DefaultSqlSession.StrictMap) object;
                List list = (List) map.get("list");
                for (Object o : list) {
                    reflectCreateInfo(o);
                }
            } else {
                // 单行
                reflectCreateInfo(object);
            }
        } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            // update操作
            if (object instanceof DefaultSqlSession.StrictMap) {
                // 多行
                Map map = (DefaultSqlSession.StrictMap) object;
                List list = (List) map.get("list");
                for (Object o : list) {
                    reflectUpdateInfo(o);
                }
            } else {
                // 单行
                reflectUpdateInfo(object);
            }
        }
        return invocation.proceed();

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 设置 createTime&createUser
     * @param object obj
     */
    private void reflectCreateInfo(Object object) {
        if (object instanceof BaseEntity) {
            BaseEntity e = (BaseEntity) object;
            e.setCreateUser(100000L);
            e.setCreateTime(new Date());
        }
    }

    /**
     * 设置 updateTime&updateUser
     * @param object obj
     */
    private void reflectUpdateInfo(Object object) {
        if (object instanceof BaseEntity) {
            BaseEntity e = (BaseEntity) object;
            e.setUpdateUser(100000L);
            e.setUpdateTime(new Date());
        }
    }

}
