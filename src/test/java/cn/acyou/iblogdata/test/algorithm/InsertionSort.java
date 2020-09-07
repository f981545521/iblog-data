package cn.acyou.iblogdata.test.algorithm;

import java.util.Arrays;

/**
 * @author youfang
 * @version [1.0.0, 2020-9-7 下午 08:22]
 **/
public class InsertionSort implements IArraySort {
    private static int[] numbers = new int[]{23, 344, 45, 56, 6786, 9, 345, 123, 236, 45, 53, 56, 12, 345, 672, 237, 238, 1123};

    public static void main(String[] args) {
        IArraySort arraySort = new InsertionSort();
        int[] sort = arraySort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }
}
