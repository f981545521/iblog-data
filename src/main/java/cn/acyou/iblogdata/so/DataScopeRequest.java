package cn.acyou.iblogdata.so;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2019-07-25 10:27]
 * @since [ERP服务]
 **/
public class DataScopeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 数据权限SQL */
    private String dataScopeSql;

    public String getDataScopeSql() {
        return dataScopeSql;
    }

    public void setDataScopeSql(String dataScopeSql) {
        this.dataScopeSql = dataScopeSql;
    }
}
