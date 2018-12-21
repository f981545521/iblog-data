package cn.acyou.iblogdata.spring;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-20 下午 05:37]
 **/
public class UniqueBean {
    private String beanName;

    public UniqueBean() {

    }

    public UniqueBean(String name) {
        this.beanName = name;
    }

    public void print(){
        System.out.println("HELLO :" + beanName);
    }
}
