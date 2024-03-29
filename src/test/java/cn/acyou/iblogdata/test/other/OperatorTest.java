package cn.acyou.iblogdata.test.other;

import org.junit.Test;

/**
 * @author youfang
 * @version [1.0.0, 2020/5/6]
 **/
public class OperatorTest {

    public static void main(String[] args) {

    }

    /**
     * &（按位与）
     *
     * &按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，运算规则即(两个为真才为真)1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     *
     * 3的二进制位是0000 0011 ， 5的二进制位是0000 0101 ， 那么就是011 & 101，由按位与运算规则得知，001 & 101等于0000 0001，最终值为1
     *
     * 7的二进制位是0000 0111，那就是111 & 101等于101，也就是0000 0101，故值为5
     */
    @Test
    public void test1(){
        System.out.println(233245&5);
        System.out.println(123&5);
        System.out.println(456&5);
        System.out.println(345&5);
        System.out.println(567&5);

        System.out.println(true&true);
        System.out.println(true&false);
        System.out.println(true&&false);
    }
    /**
     * |（按位与）
     *
     * |按位或和&按位与计算方式都是转换二进制再计算，不同的是运算规则(一个为真即为真)1|0 = 1 , 1|1 = 1 , 0|0 = 0 , 0|1 = 1
     *
     * 6的二进制位0000 0110 , 2的二进制位0000 0010 , 110|010为110，最终值0000 0110，故6|2等于6
     */
    @Test
    public void test2(){
        System.out.println(233245|5);
        System.out.println(123|5);
        System.out.println(456|5);
        System.out.println(345|5);
        System.out.println(567|5);

        System.out.println(3^2);
        System.out.println(4^2);
        System.out.println(5^2);
    }
    /**
     * ^（异或运算符）
     *
     * ^异或运算符顾名思义，异就是不同，其运算规则为1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     *
     * 5的二进制位是0000 0101 ， 9的二进制位是0000 1001，也就是0101 ^ 1001,结果为1100 , 00001100的十进制位是12
     */
    @Test
    public void test3(){
        System.out.println(3^2);
        System.out.println(4^2);
        System.out.println(5^2);
    }

    /**
     * <<（左移运算符）
     *
     * 5<<2的意思为5的二进制位往左挪两位，右边补0，5的二进制位是0000 0101 ， 就是把有效值101往左挪两位就是0001 0100 ，正数左边第一位补0，负数补1，等于乘于2的n次方，十进制位是20
     */
    @Test
    public void test4(){
        System.out.println(3<<2);
        System.out.println(4<<2);
        System.out.println(2<<3);
    }

    /**
     * >>（右移运算符）
     *
     * 凡位运算符都是把值先转换成二进制再进行后续的处理，5的二进制位是0000 0101，右移两位就是把101左移后为0000 0001，正数左边第一位补0，负数补1，等于除于2的n次方，结果为1
     */
    @Test
    public void test5(){
        System.out.println(3>>2);
        System.out.println(4>>2);
        System.out.println(2>>3);
    }


    /**
     * ~（取反运算符）
     * 取反就是1为0,0为1,5的二进制位是0000 0101，取反后为1111 1010，值为-6
     */
    @Test
    public void test6(){
        System.out.println(~3);
        System.out.println(~4);
        System.out.println(~5);
        System.out.println(~6);
    }

    /**
     * >>>（无符号右移运算符）
     *
     * 正数无符号右移
     *
     * 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
     *
     * 15的二进制位是0000 1111 ， 右移2位0000 0011，结果为3
     */
    @Test
    public void test7(){
        System.out.println(18>>>2);
        System.out.println(18>>>3);
        System.out.println(64>>>3);
        System.out.println(98>>>2);
    }

    @Test
    public void test8(){
        System.out.println(18>2);//true
        System.out.println(18>>2);//4
        System.out.println(18>>>2);//4
    }
}
