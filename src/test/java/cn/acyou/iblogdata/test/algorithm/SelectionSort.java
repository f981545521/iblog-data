package cn.acyou.iblogdata.test.algorithm;

import java.util.Arrays;

/**
 * @author youfang
 * @version [1.0.0, 2020-9-7 下午 08:17]
 **/
public class SelectionSort implements IArraySort {
    private static int[] numbers = new int[]{23, 344, 45, 56, 6786, 9, 345, 123, 236, 45, 53, 56, 12, 345, 672, 237, 238, 1123};

    public static void main(String[] args) {
        IArraySort arraySort = new SelectionSort();
        int[] sort = arraySort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }


    @Override
    public int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }
}
