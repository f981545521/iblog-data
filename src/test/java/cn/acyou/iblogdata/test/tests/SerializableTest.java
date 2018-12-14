package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.test.entity.Animal;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
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
