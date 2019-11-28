package cn.acyou.iblogdata.test.tests;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

/**
 * @author youfang
 * @version [1.0.0, 2019-06-10 16:46]
 * @since [司法公证]
 **/
public class HuToolTest {

    @Test
    public void test1(){
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(bStr);

        double aa = 67556.32;

        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(aa);
        System.out.println(digitUppercase);

        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.objectId());
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());


    }

    @Test
    public void test(){
        List<String> a = new ArrayList<>();
        System.out.println(a.size());
    }
    @Test
    public void test2(){
        int drowRowCount =2;
        for (int i = 0; i < drowRowCount; i++) {
            System.out.println("OK");
        }
    }
    @Test
    public void test3(){

        System.out.println(5/4);//2
        System.out.println(6/4);//2
        System.out.println(7/4);//2
        System.out.println(9/4);//3
        System.out.println(10/4);//3
        System.out.println(11/4);//3
        System.out.println(13/4);//4
        System.out.println((17/4) + 1);//4
    }
    @Test
    public void test4(){
        double a1 = 5;
        double b1 = 4;
        for (int i = 0;i<10;i++){
            //System.out.println(a1/b1);
            System.out.println(Math.floor(a1/b1));
            a1 ++;
        }
        //System.out.println(5/4);//2
        //System.out.println(6/4);//2
        //System.out.println(7/4);//2
        //System.out.println(8/4);//2
        //System.out.println(9/4);//3
        //System.out.println(10/4);//3
        //System.out.println(11/4);//3
        //System.out.println(12/4);//3
        //System.out.println(13/4);//3
    }

    @Test
    public void test5(){

        System.out.println(0%4);//2
        System.out.println(1%4);//2
        System.out.println(2%4);//2
        System.out.println(3%4);//2
        System.out.println(4%4);//2
        System.out.println(5%4);//2
        System.out.println(6%4);//2
        System.out.println(7%4);//2
        System.out.println(9%4);//3
    }
    @Test
    public void test6(){

        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");

        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("8");

        stringList.add("9");
        stringList.add("10");
        stringList.add("11");


        List<String> tablePerson = new ArrayList<>();
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            tablePerson.add(iterator.next());
            if (tablePerson.size() == 4 && iterator.hasNext()){
                //需要新增页数
                //。。。
                System.out.println("插入数据并新增TABLE");
                System.out.println(tablePerson);
                tablePerson.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(tablePerson)){
            System.out.println("插入数据");
            System.out.println(tablePerson);
            tablePerson.clear();
        }
    }
    @Test
    public void test7(){

        List<String> stringList1 = new ArrayList<>();
        stringList1.add("1");
        stringList1.add("2");
        stringList1.add("3");
        stringList1.add("4");

        stringList1.add("5");
        stringList1.add("6");
        stringList1.add("7");
        stringList1.add("8");

        stringList1.add("9");
        stringList1.add("10");
        stringList1.add("11");


        List<String> stringList2 = new ArrayList<>();
        stringList2.add("21");
        stringList2.add("22");
        stringList2.add("23");
        stringList2.add("24");

        List<String> tablePerson = new ArrayList<>();
        List<String> table2Person = new ArrayList<>();
        Iterator<String> iterator = stringList1.iterator();
        while (iterator.hasNext()){
            tablePerson.add(iterator.next());
            if (tablePerson.size() == 4 && iterator.hasNext()){
                //需要新增页数
                //。。。
                System.out.println("插入数据并新增TABLE");
                System.out.println(tablePerson);
                tablePerson.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(tablePerson)){
            System.out.println("插入数据");
            System.out.println(tablePerson);
            tablePerson.clear();
        }
    }
    @Test
    public void test8(){
        Deque<String> stringDeque = new LinkedList<>();
        stringDeque.add("11");
        stringDeque.add("12");
        List<String> tableDlrPerson = new ArrayList<>();
        List<String> stringList = pop5Person(stringDeque, tableDlrPerson);
        System.out.println(stringList);
        System.out.println(tableDlrPerson);

    }

    @Test
    public void test9(){
        String a = "xxx";
        String[] split = a.split(",");
        System.out.println(split);

    }




    @Test
    public void test8_1(){
        Deque<String> stringDeque = new LinkedList<>();
        stringDeque.add("11");
        stringDeque.add("12");
        stringDeque.add("13");
        stringDeque.add("14");
        System.out.println(stringDeque.poll());
        System.out.println(stringDeque);
        System.out.println(stringDeque.poll());
        System.out.println(stringDeque);
        System.out.println(stringDeque.poll());
        System.out.println(stringDeque);
        System.out.println(stringDeque.size());
    }

    private List<String> pop5Person(Deque<String> sqrPersonDeque, List<String> tableDlrPerson) {
        List<String> dossierPeople = new ArrayList<>();
        for (int i =0;i<5;i++){
            String pollPerson = sqrPersonDeque.poll();
            if (pollPerson != null){
                if (pollPerson.equals("12")){
                    tableDlrPerson.add(pollPerson);
                }
                dossierPeople.add(pollPerson);
            }
        }
        return dossierPeople;
    }
}
