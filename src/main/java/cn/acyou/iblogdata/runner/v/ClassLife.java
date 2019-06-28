package cn.acyou.iblogdata.runner.v;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Properties;


/**
 * @author youfang
 * @version [1.0.0, 2019-06-28 16:30]
 * @since [ERP服务]
 **/
@Component
public class ClassLife implements Serializable {
    private static final long serialVersionUID = -5963296099071967893L;

    private static ClassLife INNER_CLASS = null;

    private static ClassLifeInnerClass lifeInnerClass = new ClassLifeInnerClass();

    private static Properties properties = new Properties();

    private Integer id;
    private String name;

    /**
     * 静态代码块：他在类中的成员位置，用“｛｝”括起来的代码。只不过他用了static修饰了，且执行一次
     */
    static {
        properties.setProperty("x", "小明");
        properties.setProperty("y", "小张");
        properties.setProperty("z", "小飞");
    }

    /**
     * 构造代码块：是在类中直接定义的，用“｛｝”括起来的代码。每次调用构造方法前执行，都会先执行构造代码块。
     * 构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行。
     * 会被编译到构造方法中
     * 如：
     *     public ClassLife() {
     *         System.out.println("代码块执行了！！");
     *         INNER_CLASS = new ClassLife(1, "INNER_CLASS");
     *         System.out.println("ClassLife 初始化。。。");
     *     }
     */
    {
        System.out.println("代码块执行了！！");
    }

    public ClassLife() {
        INNER_CLASS = new ClassLife(1, "INNER_CLASS");
        System.out.println("ClassLife 初始化。。。");
    }

    public ClassLife(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String sayHello(){
        return "HELLO !";
    }

    /** 内部类 */
    static class ClassLifeInnerClass {
        public ClassLifeInnerClass() {
            /*
             * 构造子类之前必先构造父类！！
             */
            System.out.println("ClassLifeInnerClass 内部类创建 ");
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassLife{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("ClassLife对象销毁 -> 调用 finalize 方法");
        super.finalize();
    }
}
