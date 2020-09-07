package cn.acyou.iblogdata.test.algorithm;

import java.util.Arrays;

/**
 * @author youfang
 * @version [1.0.0, 2020-9-7 下午 08:26]
 **/
public class MergeSort implements IArraySort {
    private static int[] numbers = new int[]{23, 344, 45, 56, 6786, 9, 345, 123, 236, 45, 53, 56, 12, 345, 672, 237, 238, 1123};

    public static void main(String[] args) {
        IArraySort arraySort = new MergeSort();
        int[] sort = arraySort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

}
