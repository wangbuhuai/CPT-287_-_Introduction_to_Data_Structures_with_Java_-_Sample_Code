// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-16

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.Arrays;

/** An array list with iterator */
public class Array_List<T> implements Iterable<T> {
    // Data fields
    protected Object[] data;  // The values stored in the array list
    protected int capacity;  // The capacity of the array list
    protected int numOfItems;  // The size of the array list
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity for an empty array list
    
    // Constructors
    
    public Array_List() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
    }
    
    public Array_List(Array_List<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null array list"); }
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        data = Arrays.copyOf(other.data, capacity);
    }
    
    // Methods
    
    /** Returns the size of the array list.
        @return: size of the array list
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)
    
    /** Tests whether the array list is empty or not.
        @return: {true} if the array list is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)
    
    /** Returns the value at specified index.
        @param index: the index of the value to return
        @return: the value at specified index
        @throws IndexOutOfBoundsException: index < 0 or index >= size
    */
    @SuppressWarnings("unchecked")
    public final T get(int index) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        return (T)data[index];
    }  // Time complexity: O(1)
    
    /** Updates the value at specified index.
        @param index: the index of the value to update
        @param value: the new value to replace the value at the index
        @throws IndexOutOfBoundsException: index < 0 or index >= size
    */
    public void set(int index, T value) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        data[index] = value;
    }  // Time complexity: O(1)
    
    /** Doubles the capacity without changing the current values. */
    protected final void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, size());
        data = newData;
    }  // Time complexity: O(n)
    
    /** Inserts a new value to the rear end of the array list.
        @param value: the new value to insert to the array list
        @return: always {true}
    */
    public boolean add(T value) {
        if (numOfItems == capacity) { reserve(); }
        data[numOfItems++] = value;
        return true;
    }  // Time complexity: O(1)
    
    /** Inserts a new value at specified index into the array list.
        @param index: the index to insert the new value
        @param value: the new value to insert into the array list
        @throws IndexOutOfBoundsException: index < 0 || index > size
    */
    public void insert(int index, T value) {
        if (index < 0 || index > size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        if (numOfItems == capacity) { reserve(); }
        System.arraycopy(data, index, data, index + 1, size() - index);  // Data shift
        data[index] = value;
        numOfItems++;
    }  // Time complexity: O(n)
    
    /** Removes the value at specified index from the array list.
        @param index: the index of the value to remove
        @return: the value removed
        @throws IndexOutOfBoundsException: index < 0 or index >= size
    */
    public final T remove(int index) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        T toBeRemoved = get(index);
        System.arraycopy(data, index + 1, data, index, size() - index - 1);  // Data shift
        numOfItems--;
        return toBeRemoved;
    }  // Time complexity: O(n)
    
    /** Returns the index of a target value (first occurrence) in the array list.
        @param target: the target value to search in the array list
        @return: the index of the target value (first occurrence) in the array list;
                 or {-1} if the target value does not appear in the array list
    */
    public int indexOf(T target) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(target)) { return i; }
        }
        return -1;
    }  // Time complexity: O(n)
    
    /** Tests whether a target value appears in the array list or not.
        @param target: the target value to search in the array list
        @return: {true} if the target value appears in the array list; {false} otherwise
    */
    public boolean contains(T target) { return indexOf(target) != -1; }  // Time complexity: O(n)

    /** Removes all the values from the array list. */
    public final void clear() { numOfItems = 0; }  // Time complexity: O(1)

    /** Customizes the output format for the array list.
        @return: a string representing the output format of the array list
    */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        for (int i = 0; i < size(); i++) {
            builder.append(get(i));
            if (i != size() - 1) { builder.append(", "); }
        }
        return builder.append(']').toString();
    }  // Time complexity: O(n)

    /** Generates a list iterator positioned at the beginning of the array list.
        @return: a list iterator positioned at the beginning of the array list
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private int nextIndex = 0;  // The index on the right of the iterator
            private int prevIndex = -1;  // The index on the left of the iterator

            /** Tests whether there exists a next value at current iterator position.
                @return: {true} if there exists a next value; {false} otherwise
            */
            @Override
            public boolean hasNext() { return nextIndex != size(); }  // Time complexity: O(1)

            /** Tests whether there exists a previous value at current iterator position.
                @return: {true} if there exists a previous value; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return prevIndex != -1; }  // Time complexity: O(1)

            /** Moves the iterator forward one position and returns the value passed by.
                @return: the value passed by during the iterator movement
                @throws IndexOutOfBoundsException: the iterator is at the end of the array list.
            */
            @Override
            public T next() {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + nextIndex); }
                prevIndex++;
                nextIndex++;
                return get(prevIndex);
            }  // Time complexity: O(1)

            /** Moves the iterator backword one position and returns the value passed by.
                @return: the value passed by during the iterator movement
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the array list.
            */
            @Override
            public T previous() {
                if (!hasPrevious()) { throw new IndexOutOfBoundsException("Index out of bounds" + prevIndex); }
                nextIndex--;
                prevIndex--;
                return get(nextIndex);
            }  // Time complexity: O(1)

            /** Updates the next value at current iterator position.
                @param value: the new value to replace the next value
                @throws IndexOutOfBoundsException: the iterator is at the end of the array list.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + nextIndex); }
                set(nextIndex, value);
                next();
            }  // Time complexity: O(1)

            /** Updates the previous value at current iterator position.
                @param value: the new value to replace the previous value
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the array list.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + prevIndex); }
                set(prevIndex, value);
            }  // Time complexity: O(1)

            /** Removes the next value at current iterator position.
                @return: the value removed
                @throws IndexOutOfBoundsException: the iterator is at the end of the array list.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + nextIndex); }
                return remove(nextIndex);
            }  // Time complexity: O(n)

            /** Removes the previous value at current iterator position.
                @return: the value removed
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the array list.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new IndexOutOfBoundsException("Index out of bounds: " + prevIndex); }
                previous();
                return removeNext();
            }  // Time complexity: O(n)

            /** Inserts a new value at current iterator position.
                @param value: the new value to insert into the array list.
            */
            @Override
            public void add(T value) {
                insert(nextIndex, value);
                next();
            }  // Time complexity: O(n)

            /** Moves the iterator to the beginning of the array list. */
            @Override
            public void reset() {
                prevIndex = -1;
                nextIndex = 0;
            }  // Time complexity: O(1)
        };
    }  // Time complexity: O(1)
}