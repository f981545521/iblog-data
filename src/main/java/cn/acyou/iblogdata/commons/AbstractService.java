package cn.acyou.iblogdata.commons;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 *
 * @author youfang
 */
@Transactional(timeout = 1, rollbackFor = Exception.class)
public abstract class AbstractService<T extends Po, PK extends Serializable> implements Service<T, PK> {

    @Autowired
    protected Mapper<T,PK> mapper;

    protected Class<T> poClazz;

    protected Logger logger;

    public AbstractService() {
        TypeToken<T> poType = new TypeToken<T>(getClass()) {
        };
        logger = LoggerFactory.getLogger(getClass().getName());
        poClazz = (Class<T>) poType.getRawType();
    }

    @Override
    public int saveSelective(T model) {
        return mapper.insertSelective(model);
    }

    @Override
    public int save(List<T> models) {
        return mapper.insertList(models);
    }


    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public int updateByPrimaryKeySelective(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int updateByPrimaryKey(T model) {
        return mapper.updateByPrimaryKey(model);
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public int selectCount(T v) {
        return mapper.selectCount(v);
    }

    @Override
    public int deleteById(PK id) {
        return mapper.deleteByIds(String.valueOf(id));
    }

    @Override
    public T findById(PK id) {
        return mapper.selectByPrimaryKey(String.valueOf(id));
    }
}
