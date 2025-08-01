// Created by Dayu Wang (dwang@stchas.edu) on 2025-07-07

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-07-07


public class Bitwise_Operation {
    /** Tests whether a non-negative integer is odd or even.
        @param n: the non-negative integer to test
        @return: {true} if the integer is odd; {false} if the integer is even
    */
    public static boolean isOdd(int n) { return (n & 1) == 1; }  // Time complexity: O(1)

    /** Tests whether an integer is a power of two.
        @param n: the integer to test
        @return: {true} if the integer is power of two; {false} otherwise
    */
    public static boolean isPowerOfTwo(int n) {
        if (n < 1) { return false; }
        return (n & n - 1) == 0;
    }  // Time complexity: O(1)

    /** Returns the only element that appears only once in an array.  All other elements appear exactly twice.
        @param arr: a non-empty array in which every element appears twice in it, except for one which appears only once
        @return: the only element that appears only once in an array
    */
    public static int singleNumber(int[] arr) {
        int result = 0;
        for (int val: arr) { result ^= val; }
        return result;
    }  // Time complexity: O(1)
}