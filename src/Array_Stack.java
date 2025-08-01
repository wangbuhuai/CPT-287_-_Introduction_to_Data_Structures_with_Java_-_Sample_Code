// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-27

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.Arrays;
import java.util.NoSuchElementException;

/** A stack implemented by array */
public class Array_Stack<T> {
    // Data fields
    private Object[] data;  // The values stored in the stack
    private int capacity;  // The capacity of the array
    private int numOfItems;  // The number of items in the stack
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity for an empty array

    // Constructors

    public Array_Stack() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
    }

    public Array_Stack(Array_Stack<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null stack"); }
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        data = Arrays.copyOf(other.data, capacity);
    }

    // Methods

    /** Returns the size of the stack.
        @return: the size of the stack
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the stack is empty or not.
        @return: {true} if the stack is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value at the top of the stack.
        @return: the value at the top of the stack
        @throws NoSuchElementException: the stack is empty.
    */
    @SuppressWarnings("unchecked")
    public final T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        return (T)data[size() - 1];
    }  // Time complexity: O(1)

    /** Removes the value at the top of the stack.
        @return: the value removed;
        @throws NoSuchElementException: the stack is empty.
    */
    @SuppressWarnings("unchecked")
    public final T pop() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        return (T)data[--numOfItems];
    }  // Time complexity: O(1)

    /** Doubles the capacity of the array without changing the data. */
    private void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        System.arraycopy(data,0, newData, 0, size());
        data = newData;
    }  // Time complexity: O(n)

    /** Inserts a new value onto the top of the stack.
        @param value: the new value to insert onto the stack
    */
    public final void push(T value) {
        if (size() == capacity) { reserve(); }
        data[numOfItems++] = value;
    }  // Time complexity: O(1)
}