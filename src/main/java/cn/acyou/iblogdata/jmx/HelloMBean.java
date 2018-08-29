package cn.acyou.iblogdata.jmx;

/**
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 10:50]
 **/
public interface HelloMBean {
    //管理属性
    public void setName(String name);

    public String getName();

    //管理操作
    public String print();
}