package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.so.DataScopeRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * MyBatis 拦截器，用于组装数据权限SQL
 *
 * @author youfang
 * @version [1.0.0, 2019-07-22 19:33]
 * @since [ERP服务]
 **/
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class DataScopeInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = null;
        if (invocation.getArgs().length > 1) {
            parameterObject = invocation.getArgs()[1];
            if (parameterObject instanceof DataScopeRequest) {
                String scopeSql = ((DataScopeRequest) parameterObject).getDataScopeSql();
                if (StringUtils.isNotEmpty(scopeSql)) {
                    BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
                    Configuration configuration = mappedStatement.getConfiguration();

                    String sql = boundSql.getSql();
                    String sql2Reset = sql + scopeSql;
                    // 包装sql后，重置到invocation中
                    resetSql2Invocation(invocation, configuration, sql2Reset, boundSql.getParameterMappings());
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 包装sql后，重置到invocation中
     *
     * @param invocation
     * @param sql
     * @throws SQLException
     */
    private void resetSql2Invocation(Invocation invocation, Configuration configuration, String sql, List<ParameterMapping> parameterMappingList) {
        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        MappedStatement newStatement = newMappedStatement(statement, new StaticSqlSource(configuration, sql, parameterMappingList));
        args[0] = newStatement;
    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder =
                new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }


}
