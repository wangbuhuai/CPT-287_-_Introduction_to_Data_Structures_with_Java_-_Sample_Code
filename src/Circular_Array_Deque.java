// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.Arrays;
import java.util.NoSuchElementException;

/** A deque implemented by circular array */
public class Circular_Array_Deque<T> {
    // Data fields
    private Object[] data;  // The values stored in the deque
    private int capacity;  // The capacity of the array
    private int numOfItems;  // Number of values in the deque
    private int frontIndex;  // The index of the value at the front end of the deque
    private int rearIndex;  // The index of the value at the rear end of the deque
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity for an empty array

    // Constructors

    public Circular_Array_Deque() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
        rearIndex = -1;
    }  // Time complexity: O(1)

    public Circular_Array_Deque(Circular_Array_Deque<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null deque"); }
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        frontIndex = other.frontIndex;
        rearIndex = other.rearIndex;
        data = Arrays.copyOf(other.data, capacity);
    }

    // Methods

    /** Returns the size of the deque.
        @return: the size of the deque
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the deque is empty or not.
        @return: {true} if the deque is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value at the front end of the deque.
        @return: the value at the front end of the deque
        @throws NoSuchElementException: the deque is empty.
    */
    @SuppressWarnings("unchecked")
    public final T peekFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return (T)data[frontIndex];
    }  // Time complexity: O(1)

    /** Returns the value at the rear end of the deque.
        @return: the value at the rear end of the deque
        @throws NoSuchElementException: the deque is empty.
    */
    @SuppressWarnings("unchecked")
    public final T peekLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return (T)data[rearIndex];
    }  // Time complexity: O(1)

    /** Removes the value at the front end of the deque.
        @return: the value removed
        @throws NoSuchElementException: the deque is empty.
    */
    @SuppressWarnings("unchecked")
    public final T pollFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = (T)data[frontIndex];
        if (numOfItems-- == 1) {
            frontIndex = 0;
            rearIndex = -1;
        } else { frontIndex = (frontIndex + 1) % capacity; }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Removes the value at the rear end of the deque.
        @return: the value removed
        @throws NoSuchElementException: the deque is empty.
    */
    @SuppressWarnings("unchecked")
    public final T pollLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = (T)data[rearIndex];
        if (numOfItems-- == 1) {
            frontIndex = 0;
            rearIndex = -1;
        } else { rearIndex = (rearIndex - 1 + capacity) % capacity; }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Doubles the capacity of the array and rearranges the values in the deque. */
    private void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        for (int i = 0, k = frontIndex; i < size(); i++, k = (k + 1) % (capacity / 2)) { newData[i] = data[k]; }
        data = newData;
        frontIndex = 0;
        rearIndex = size() - 1;
    }  // Time complexity: O(n)

    /** Inserts a new value to the front end of the deque.
        @param value: the new value to insert to the deque
    */
    public final void offerFirst(T value) {
        if (size() == capacity) { reserve(); }
        frontIndex = (frontIndex - 1 + capacity) % capacity;
        data[frontIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)

    /** Inserts a new value to the rear end of the deque.
        @param value: the new value to insert to the deque
    */
    public final void offerLast(T value) {
        if (size() == capacity) { reserve(); }
        rearIndex = (rearIndex + 1) % capacity;
        data[rearIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)
}