package cn.acyou.iblogdata.spring;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2019-06-10 17:03]
 **/
@Slf4j
public class ScopeBean implements Serializable {
    private static final long serialVersionUID = -4337156325822422561L;
    private String beanName;

    public ScopeBean() {

    }

    public ScopeBean(String name) {
        this.beanName = name;
        log.info("初始化 :" + name);
    }

    public void print(){
        log.info("HELLO :" + beanName);
    }

}
