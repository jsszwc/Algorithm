package org.example.sort;

public class QuickSort {

    public static void sort(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }
        quickSort(arr, l, r-1);
    }

    public static void sort(int[] arr) {
        if(arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }

        int pos = partition(arr, l, r);
        quickSort(arr, l, pos-1);
        quickSort(arr, pos+1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        while(l < r) {
            //注意以下两个while中的等于号。当有多个等于pivot的数字时：
            //如果写了两个等于号，那么本次partition执行后这些相等数字可能分布于pivot的两侧
            //如果只写了个一个等于号，那么本次partition执行后这些相等数字只会分布于pivot的一侧，具体是哪侧要看等于号写在哪个while中
            //如果没写等于号，那么会陷入死循环
            while(l < r && arr[r] >= pivot) {
                --r;
            }
            arr[l] = arr[r];

            while(l < r && arr[l] <= pivot) {
                ++l;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        return l;
    }
}
