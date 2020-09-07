package cn.acyou.iblogdata.test.algorithm;

import java.util.Arrays;

/**
 * @author youfang
 * @version [1.0.0, 2020-9-7 下午 08:29]
 **/
public class CountingSort implements IArraySort {
    private static int[] numbers = new int[]{23, 344, 45, 56, 6786, 9, 345, 123, 236, 45, 53, 56, 12, 345, 672, 237, 238, 1123};

    public static void main(String[] args) {
        IArraySort arraySort = new CountingSort();
        int[] sort = arraySort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxValue = getMaxValue(arr);

        return countingSort(arr, maxValue);
    }

    private int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];

        for (int value : arr) {
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

}
