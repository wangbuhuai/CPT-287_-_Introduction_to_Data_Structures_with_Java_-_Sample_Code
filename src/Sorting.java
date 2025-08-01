// Created by Dayu Wang (dwang@stchas.edu) on 2025-08-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-08-01


import java.util.Arrays;

public class Sorting {
    // Selection sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Find the minimum value in the rest of the array.
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) { min = j; }
            }
            // Swap if necessary.
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }  // Time complexity: O(n ^ 2)

    // Bubble sort (improved version)
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) { return; }
        }
    }  // Time complexity: O(n ^ 2)

    // Insertion sort
    public static void insertionSort(int[] arr) {
        for (int cur = 1; cur < arr.length; cur++) {
            int mark = arr[cur], j;
            for (j = cur; j > 0 && arr[j - 1] > mark; j--) { arr[j] = arr[j - 1]; }
            arr[j] = mark;
        }
    }  // Time complexity: O(n ^ 2)

    /** Merges two sorted arrays into a single sorted array.
        @param arr1: first sorted array to merge
        @param arr2: second sorted array to merge
        @param arr3: merged sorted array
    */
    private static void merge(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) { arr3[k++] = arr1[i++]; }
            else { arr3[k++] = arr2[j++]; }
        }
        while (i < arr1.length) { arr3[k++] = arr1[i++]; }
        while (j < arr2.length) { arr3[k++] = arr2[j++]; }
    }  // Time complexity: O(n)

    // Merge sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) { return; }  // Base case
        int[] leftHalf = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightHalf = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        mergeSort(leftHalf);  // Recursion
        mergeSort(rightHalf);  // Recursion
        merge(leftHalf, rightHalf, arr);
    }  // Time complexity: O(n * log(n))
}