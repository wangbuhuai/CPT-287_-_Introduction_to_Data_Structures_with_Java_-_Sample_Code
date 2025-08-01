// Created by Dayu Wang (dwang@stchas.edu) on 2025-08-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-08-01


public class Heap_Sort {
    /** Moves the root to form a max heap.
        @param arr: array to max-heapify
        @param size: size of the array
        @param root: index of the root
    */
    private static void maxHeapify(int[] arr, int size, int root) {
        // Find the children of the root.
        int left = 2 * root + 1, right = 2 * root + 2;
        // Find the maximum value among root, left, and right.
        int max = root;
        if (left < size && arr[left] > arr[max]) { max = left; }
        if (right < size && arr[right] > arr[max]) { max = right; }
        // If the max is not the root, swap the root with its larger child.
        if (root != max) {
            int temp = arr[root];
            arr[root] = arr[max];
            arr[max] = temp;
            // Recursively apply the same algorithm in the next level.
            maxHeapify(arr, size, max);
        }
    }  // Time complexity: O(log(n))

    /** Converts an array to a max heap.
        @param arr: array to build max heap
        @param root: index of the root
    */
    private static void buildMaxHeap(int[] arr, int root) {
        // Find the roots of left and right subtrees.
        int left = 2 * root + 1, right = 2 * root + 2;
        // Recursively build max heaps for the left and right subtrees.
        if (left < arr.length) { buildMaxHeap(arr, left); }
        if (right < arr.length) { buildMaxHeap(arr, right); }
        // Finally, relocate the root to make the entire array a max heap.
        maxHeapify(arr, arr.length, root);
    }  // Time complexity: O(n)

    /** Sorts an array.
        @param arr: array to sort
        @param size: size of the array
    */
    private static void sort(int[] arr, int size) {
        // Converts the array to a max heap.
        buildMaxHeap(arr, 0);
        for (int j = size - 1; j > 0; j--) {
            // Swap the elements at indices 0 and j.
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            // Shrink the size by 1 and call maxHeapify() to re-heap the array.
            maxHeapify(arr, --size, 0);
        }
    }  // Time complexity: O(n * log(n))

    // Wrapper method
    public static void sort(int[] arr) { sort(arr, arr.length); }  // Time complexity: O(n * log(n))
}