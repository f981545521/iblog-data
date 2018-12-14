package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.test.entity.Animal;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 *   因为在你没有显式的声明序列号时，在程序编译时候会自动生成一个序列号，存储在文件中，
 *   但是在你更改了实体类的时候又会重新生成一个序列号，在程序运行的时候，
 *   Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
 *
 *   在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
 *   如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。
 *   也就是说，在你读取这个object的时候，他会比较你的现在这个类的序列号和你存储的那个序 列号是否相等，
 *   如果相等，则说明这是同一个版本，如果不相等则是不同版本，
 *   不同版本之间的成员变量是不相同的，所以就会报错，但是当你显式的声明了这个序列号时，
 *   不论你如何修改类中的成员变量还是方法，都不会引起版本之间不兼容得问题，这个就加强了你的程序的健壮性。
 * @author youfang
 * @version [1.0.0, 2018-12-14 下午 03:00]
 **/
public class SerializableTest {

    @Test
    public void testSerialise() throws Exception{
        Animal an = new Animal();
        an.setName("旺财");
        FileOutputStream f = new FileOutputStream("F:\\iotest\\animal");
        ObjectOutputStream oos = new ObjectOutputStream(f);
        oos.writeObject(an);
        oos.close();
    }

    @Test
    public void testDeserialize() throws Exception{
        FileInputStream fis = new FileInputStream("F:\\iotest\\animal");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Animal ani = (Animal)ois.readObject();
        System.out.println(ani.getName());
        ois.close();
        fis.close();
    }

}
