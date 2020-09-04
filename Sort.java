package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 21:19
 * @description: https://blog.csdn.net/weixin_41190227/article/details/86600821
 */


public class Sort {

    public static void main(String[] args) {
        int[] arr = heapSort(new int[]{8,9,1,7,2,3,5,4,6,0});
        for (int i : arr) {
            System.out.print(i + " ");
        }

        List list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        System.out.println(list);



    }

    /** 稳定排序 **/
    /**
     * 冒泡排序 稳定
     * 最佳情况：T(n) = O(n)
     * 最差情况：T(n) = O(n2)
     * 平均情况：T(n) = O(n2)
     * 空间复杂度：O(1)
     */
    public static int[] bubbleSort(int[] array) {
        if(array.length == 0)
            return array;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j + 1] < array[j]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }


    /**
     * 插入排序 稳定
     * 最佳情况：T(n) = O(n)
     * 最坏情况：T(n) = O(n2)
     * 平均情况：T(n) = O(n2)
     * 空间复杂度：O(1)
     */
    public static int[] insertSort(int[] array) {
        if(array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length-1; i++) {
            current = array[i+1];
            int preIndex = i;
            while(preIndex >= 0 && current < array[preIndex]) {
                array[preIndex+1] = array[preIndex];  //后移
                preIndex--;
            }
            array[preIndex + 1] = current; //插入新元素
        }
        return array;
    }


    /**
     * 归并排序 稳定
     * 最佳情况：T(n) = O(n)
     * 最差情况：T(n) = O(nlogn)
     * 平均情况：T(n) = O(nlogn)
     * 空间复杂度：O(n)
     */
    public static int[] mergeSort(int[] array) {
        if(array.length < 2)
            return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 将两段排序好的数组结合成一个排序数组
     * @param left 左有序数组
     * @param right 右有序数组
     * @return 合并后的有序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < res.length; index++) {
            if(i >= left.length)
                res[index++] = right[j++];
            else if(j >= right.length)
                res[index++] = left[i++];
            else if(left[i] > right[j])
                res[index++] = right[j++];
            else
                res[index++] = left[i++];
        }
        return res;
    }


    /**
     * 计数排序 稳定
     * 最佳情况：T(n) = O(n+k),k为最大值的位数
     * 最差情况：T(n) = O(n+k)
     * 平均情况：T(n) = O(n+k)
     * 空间复杂度：O(k)
     */
    public static int[] countSort(int[] array) {
        if(array.length == 0)
            return array;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > max)
                max = array[i];
            if(array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while(index < array.length) {
            if(bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }

    
    /**
     * 桶排序 稳定
     * 最佳情况：T(n) = O(n+k)
     * 最差情况：T(n) = O(n+k)
     * 平均情况：T(n) = O(n2)
     * 空间复杂度：O(n+k)
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> array, int bucketSize) {
        if(array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = max;
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i) > max)
                max = array.get(i);
            if(array.get(i) < min)
                min = array.get(i);
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if(bucketSize == 1) {
                for (int j = 0; j < bucketArr.get(i).size(); j++) {
                    resultArr.add(bucketArr.get(i).get(j));
                }
            } else {
                if(bucketCount == 1)
                    bucketSize--;
                ArrayList<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++) {
                    resultArr.add(temp.get(j));
                }
            }
        }
        return resultArr;
    }


    /**
     * 基数排序 稳定
     * 最佳情况：T(n) = O(n * k),k为最大数的位数
     * 最差情况：T(n) = O(n * k)
     * 平均情况：T(n) = O(n * k)
     * 空间复杂度：O(n+k)
     *
     * 基数排序有两种方法：
     * MSD 从高位开始进行排序
     * LSD 从低位开始进行排序
     *
     * 基数排序 vs 计数排序 vs 桶排序
     * 这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：
     * 基数排序： 根据键值的每位数字来分配桶
     * 计数排序： 每个桶只存储单一键值
     * 桶排序： 每个桶存储一定范围的数值
     */
    public static int[] radixSort(int[] array) {
        if(array == null || array.length < 2)
            return array;
        //1.先算出最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while(max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        return array;
    }


    /** 不稳定排序 **/
    /**
     * 选择排序 不稳定
     * 最佳情况：T(n) = O(n2)
     * 最差情况：T(n) = O(n2)
     * 平均情况：T(n) = O(n2)
     * 空间复杂度：O(1)
     */
    public static int[] selectSort(int[] array) {
        if(array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if(array[j] < array[minIndex])
                    minIndex = j;
            }
            if(minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    /**
     * 希尔排序 不稳定
     *希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；当增量减至1时，整个文件恰被分成一组，算法便终止。
     * 最佳情况：T(n) = O(nlog2 n)
     * 最坏情况：T(n) = O(nlog2 n)
     * 平均情况：T(n) =O(nlog2n)
     * 空间复杂度：O(1)
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }


    /**
     * 快速排序 不稳定
     * 最佳情况：T(n) = O(nlogn)
     * 最差情况：T(n) = O(n2)
     * 平均情况：T(n) = O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static int[] quickSort(int[] array, int start, int end) {
        if(start >= end) return array;
        int l = start + 1, r = end;
        int pilot = array[start];
        while (l <= r) {
            if(array[l] < pilot) {
                l++;
                continue;
            }
            if(array[r] >= pilot) {
                r--;
                continue;
            }
            swap(array, l, r);
        }
        swap(array, start, r);
        quickSort(array, start, r-1);
        quickSort(array, l, end);
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    /**
     *堆排序 不稳定
     * 最佳情况：T(n) = O(nlogn)
     * 最差情况：T(n) = O(nlogn)
     * 平均情况：T(n) = O(nlogn)
     * 空间复杂度：O(1)
     */

    //声明全局变量，用于记录数组array的长度
    static int len;

    public static int[] heapSort(int[] array) {
        len = array.length;
        if(len < 1) return array;
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while(len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    //建立最大堆
    public static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        //for循环这样写会更好一点：i的左子树和右子树分别2i+1和2(i+1)
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    //调整使之成为最大堆
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if(i * 2 + 2 < len && array[i * 2 + 2] > array[maxIndex])
            maxIndex = i * 2 + 2;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if(maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }
}
