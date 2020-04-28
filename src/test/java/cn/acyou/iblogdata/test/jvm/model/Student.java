package cn.acyou.iblogdata.test.jvm.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2020/4/27]
 **/
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -458747137981532030L;

    private String name;
    private Integer age;
    private List<String> interestList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<String> interestList) {
        this.interestList = interestList;
    }

    /**
     * finalize()是Object中的方法，当垃圾回收器将要回收对象所占内存之前被调用，
     * 即当一个对象被虚拟机宣告死亡时会先调用它finalize()方法，
     * 让此对象处理它生前的最后事情（这个对象可以趁这个时机挣脱死亡的命运）。
     * 要明白这个问题，先看一下虚拟机是如何判断一个对象该死的。
     *
     * 为什么应该避免使用它
     * 首先，由于finalize()方法的调用时机具有不确定性，从一个对象变得不可到达开始，到finalize()方法被执行，所花费的时间这段时间是任意长的。我们并不能依赖finalize()方法能及时的回收占用的资源，可能出现的情况是在我们耗尽资源之前，gc却仍未触发，因而通常的做法是提供显示的close()方法供客户端手动调用。
     * 另外，重写finalize()方法意味着延长了回收对象时需要进行更多的操作，从而延长了对象回收的时间。
     *
     */
    @Override
    protected void finalize() throws Throwable {
        int i = 1/0;
        System.out.println("Student " + name + " 被回收-->finalize()");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", interestList=" + interestList +
                '}';
    }
}
