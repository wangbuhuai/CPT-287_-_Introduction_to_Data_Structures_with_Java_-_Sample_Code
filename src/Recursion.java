// Created by Dayu Wang (dwang@stchas.edu) on 2025-07-05

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-07-07


import java.util.Arrays;

public class Recursion {
    /** Returns the sum of the elements in the segment from index i to j (inclusive) in an array.
        @param arr: the non-empty array the segment resides in
        @param i: the beginning index of the segment to sum
        @param j: the end index of the segment to sum
        @return: the sum of the elements in the segment
    */
    private static int sum(int[] arr, int i, int j) {
        if (i == j) { return arr[i]; }  // Base case
        else { return arr[i] + sum(arr, i + 1, j); }  // Recurrent relation
    }  // Time complexity: O(n)
    
    // Wrapper method
    /** Returns the sum of all the elements in an array of integers.
        @param arr: the non-empty array to sum
        @return: the sum of all the elements in the array
    */
    public static int sum(int[] arr) { return sum(arr, 0, arr.length - 1); }  // Time complexity: O(n)
    
    /** Returns the majority element of an array.
        @param arr: a non-empty array that has a majority element
        @return: the majority element of the array
    */
    public static int majorityElement(int[] arr) {
        if (arr.length == 1) { return arr[0]; }  // Base case
        
        // Divide the array into two halves.
        int[] leftHalf = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightHalf = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        
        // Find the "majority elements" in the left half and right half.
        int leftMajority = majorityElement(leftHalf);
        int rightMajority = majorityElement(rightHalf);
        
        // Count the occurrence of the two candidates in the entire array.
        int leftCount = 0, rightCount = 0;
        for (int val : arr) {
            if (val == leftMajority) { leftCount++; }
            if (val == rightMajority) { rightCount++; }
        }
        
        return leftCount > rightCount ? leftMajority : rightMajority;
    }  // Time complexity: O(n * log(n))

    /** Reserves a singly-linked list.
        @param head: a reference to the head node of the linked list
        @return: a reference to the head node of the reversed list
    */
    public static <T> Node<T> reverse(Node<T> head) {
        if (head == null || head.next == null) { return head; }  // Base cases
        Node<T> newHead = reverse(head.next);  // Reverse the nodes after head.
        head.next.next = head;  // Add head to the end.
        head.next = null;
        return newHead;
    }  // Time complexity: O(n)
}