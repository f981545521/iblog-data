package cn.acyou.iblogdata.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.math.BigInteger;
import java.sql.Types;

public class IblogJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {
    public IblogJavaTypeResolverDefaultImpl() {
        super();
        //bigint unsigned!
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT",
                new FullyQualifiedJavaType(BigInteger.class.getName())));
    }
}
