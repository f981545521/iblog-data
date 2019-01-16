package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.commons.So;
import cn.acyou.iblogdata.constant.AppConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author youfang
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class SoInterceptor implements Interceptor {
    private static final Joiner SORT_JOINER = Joiner.on(AppConstant.COMMA);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object paramter = invocation.getArgs()[1];
        if (paramter instanceof So) {
            So so = (So) paramter;
            Page<Object> page = PageHelper.startPage(so.getCurrentPage(), so.getPageSize(), so.isEnableCount());
            page.setOrderByOnly(so.isOrderByOnly());
            if (CollectionUtils.isNotEmpty(so.getSorts())) {
                page.setOrderBy(SORT_JOINER.join(so.getSorts()));
            }
            try {
                return invocation.proceed();
            } finally {
                PageHelper.clearPage();
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
