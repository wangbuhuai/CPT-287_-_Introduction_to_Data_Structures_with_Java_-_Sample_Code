// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.Arrays;
import java.util.NoSuchElementException;

/** A queue implemented by circular array */
public class Circular_Array_Queue<T> {
    // Data fields
    private Object[] data;  // The values stored in the queue
    private int capacity;  // The capacity of the array
    private int numOfItems;  // Number of values in the queue
    private int frontIndex;  // The index of the value at the front end of the queue
    private int rearIndex;  // The index of the value at the rear end of the queue
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity for an empty array

    // Constructors

    public Circular_Array_Queue() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
        rearIndex = -1;
    }  // Time complexity: O(1)

    public Circular_Array_Queue(Circular_Array_Queue<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null queue"); }
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        frontIndex = other.frontIndex;
        rearIndex = other.rearIndex;
        data = Arrays.copyOf(other.data, capacity);
    }

    // Methods

    /** Returns the size of the queue.
        @return: the size of the queue
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the queue is empty or not.
        @return: {true} if the queue is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value at the front end of the queue.
        @return: the value at the front end of the queue
        @throws NoSuchElementException: the queue is empty.
    */
    @SuppressWarnings("unchecked")
    public final T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return (T)data[frontIndex];
    }  // Time complexity: O(1)

    /** Removes the value at the front end of the queue.
        @return: the value removed
        @throws NoSuchElementException: the queue is empty.
    */
    @SuppressWarnings("unchecked")
    public final T poll() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        T toBeRemoved = (T)data[frontIndex];
        if (numOfItems-- == 1) {
            frontIndex = 0;
            rearIndex = -1;
        } else { frontIndex = (frontIndex + 1) % capacity; }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Doubles the capacity of the array and rearranges the values in the queue. */
    private void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        for (int i = 0, k = frontIndex; i < size(); i++, k = (k + 1) % (capacity / 2)) { newData[i] = data[k]; }
        data = newData;
        frontIndex = 0;
        rearIndex = size() - 1;
    }  // Time complexity: O(n)

    /** Inserts a new value to the rear end of the queue.
        @param value: the new value to insert to the queue
    */
    public final void offer(T value) {
        if (size() == capacity) { reserve(); }
        rearIndex = (rearIndex + 1) % capacity;
        data[rearIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)
}