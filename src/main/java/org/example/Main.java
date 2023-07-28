package org.example;

import org.example.sort.QuickSort;

public class Main {
    public static void main(String[] args) {
        int[] arr = {23, 34, 2, 236, 5634, 34, -34, -2342, -2, 23, 235, 345, 5463, 234567};
        //int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        QuickSort.sort(arr);
        for(int val : arr) {
            System.out.printf("%d ", val);
        }
    }
}