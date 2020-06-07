package cn.acyou.iblogdata.test.other;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;

/**
 * 布隆过滤器是一种数据结构，比较巧妙的概率型数据结构（probabilistic data structure），
 * 特点是高效地插入和查询，
 * 可以用来告诉你 “某样东西一定不存在或者可能存在”。
 *
 * @author youfang
 * @version [1.0.0, 2020-6-6 下午 09:16]
 **/
public class BolmFilterTest {
    //预期插入值
    private static int expectedInsertions = 10000;
    //误判率
    private static double fpp = 0.001;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, fpp);

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            //判断元素是否存在，true存在，false不存在。
            boolean isContain = bloomFilter.mightContain(i);
            if (isContain) {
                System.out.println("Init 冲突了：" + i);
            }
            bloomFilter.put(i);
        }
        int conflictCount = 0;
        for (int i = 10000; i < 20000; i++) {
            //判断元素是否存在，true存在，false不存在。
            boolean isContain = bloomFilter.mightContain(i);
            if (isContain) {
                System.out.println("冲突了：" + i);
                conflictCount++;
            }
        }
        BigDecimal val1 = new BigDecimal(conflictCount);
        BigDecimal val2 = new BigDecimal(expectedInsertions);
        System.out.println("结束，发送冲突：" + conflictCount + "，冲突率：" + val1.divide(val2));
    }


}
