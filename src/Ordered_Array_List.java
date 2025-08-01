// Created by Dayu Wang (dwang@stchas.edu) on 2020-06-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** An ordered array list with iterator */
public class Ordered_Array_List<T extends Comparable<T>> extends Array_List<T> {
    // Constructors

    public Ordered_Array_List() { super(); }  // Default constructor

    public Ordered_Array_List(Ordered_Array_List<T> other) { super(other); }  // Copy constructor

    // Methods

    /** Updates the value at specified index.
        @param index: the index of the value to update
        @param value: the new value to replace the value at the index
        @throws IndexOutOfBoundsException: index < 0 or index >= size
        @throws IllegalArgumentException: value <= get(index - 1) or value >= get(index + 1)
    */
    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        if (value.compareTo(get(index - 1)) <= 0) {
            throw new IllegalArgumentException("New value already exists or is not in order: " + value);
        }
        if (value.compareTo(get(index + 1)) >= 0) {
            throw new IllegalArgumentException("New value already exists or is not in order: " + value);
        }
        data[index] = value;
    }  // Time complexity: O(1)

    /** Inserts an item into the ordered array list.
        @param value: the new item to insert into the ordered array list
        @return: {true} if the new item has been successfully inserted;
                 {false} if the new item already exists in the list
    */
    @Override
    public boolean add(T value) {
        if (isEmpty() || value.compareTo(get(size() - 1)) >= 0) { return super.add(value); }
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(value) == 0) { return false; }
            if (get(i).compareTo(value) > 0) {
                super.insert(i, value);
                break;
            }
        }
        return true;
    }  // Time complexity: O(n)

    /** Inserts a new value at specified index into the ordered array list.
        @param index: the index to insert the new value
        @param value: the new value to insert into the array list
        @throws IndexOutOfBoundsException: index < 0 || index > size
        @throws IllegalArgumentException: the new value already exists or is not inserted in order.
    */
    @Override
    public void insert(int index, T value) {
        if (index < 0 || index > size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        if (index != 0 || value.compareTo(get(index - 1)) <= 0) {
            throw new IllegalArgumentException("New value already exists or is not inserted in order: " + value);
        }
        if (index != size() - 1 || value.compareTo(get(index + 1)) >= 0) {
            throw new IllegalArgumentException("New value already exists or is not inserted in order: " + value);
        }
        if (numOfItems == capacity) { reserve(); }
        System.arraycopy(data, index, data, index + 1, size() - index);  // Data shift
        data[index] = value;
        numOfItems++;
    }  // Time complexity: O(n)

    /** Returns the index of a target value in the ordered array list.
        @param target: the target value to search in the ordered array list
        @return: the index of the target value in the ordered array list;
                 or {-1} if the target value does not appear in the ordered array list
    */
    @Override
    public int indexOf(T target) {
        int i = 0, j = size() - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (target.compareTo(get(mid)) < 0) { j = mid - 1; }
            else if (target.compareTo(get(mid)) > 0) { i = mid + 1; }
            else { return mid; }
        }
        return -1;
    }  // Time complexity: O(log(n))

    /** Tests whether a target value appears in the ordered array list or not.
        @param target: the target value to search in the ordered array list
        @return: {true} if the target value appears in the ordered array list; {false} otherwise
    */
    @Override
    public boolean contains(T target) { return indexOf(target) != -1; }  // Time complexity: O(log(n))

    /** Generates a list iterator positioned at the beginning of the ordered array list.
        @return: a list iterator positioned at the beginning of the ordered array list
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
                @throws IndexOutOfBoundsException: the iterator is at the end of the ordered array list.
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
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the ordered array list.
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
                @throws IndexOutOfBoundsException: the iterator is at the end of the ordered array list.
                @throws IllegalArgumentException: value <= get(prevIndex) or value >= get(nextIndex + 1)
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + nextIndex); }
                if (prevIndex != -1 && value.compareTo(get(prevIndex)) <= 0) {
                    throw new IllegalArgumentException("New value already exists or is not in order: " + value);
                }
                if (nextIndex != size() - 1 && value.compareTo(get(nextIndex + 1)) >= 0) {
                    throw new IllegalArgumentException("New value already exists or is not in order: " + value);
                }
                set(nextIndex, value);
                next();
            }  // Time complexity: O(1)

            /** Updates the previous value at current iterator position.
                @param value: the new value to replace the previous value
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the ordered array list.
                @throws IllegalArgumentException: value <= get(prevIndex - 1) or value >= get(nextIndex)
            */
            @Override
            public void setPrevious(T value) {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + prevIndex); }
                if (prevIndex != 0 && value.compareTo(get(prevIndex - 1)) <= 0) {
                    throw new IllegalArgumentException("New value already exists or is not in order: " + value);
                }
                if (nextIndex != size() && value.compareTo(get(nextIndex)) >= 0) {
                    throw new IllegalArgumentException("New value already exists or is not in order: " + value);
                }
                set(prevIndex, value);
            }  // Time complexity: O(1)

            /** Removes the next value at current iterator position.
                @return: the value removed
                @throws IndexOutOfBoundsException: the iterator is at the end of the ordered array list.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new IndexOutOfBoundsException("Index out of bounds: " + nextIndex); }
                return remove(nextIndex);
            }  // Time complexity: O(n)

            /** Removes the previous value at current iterator position.
                @return: the value removed
                @throws IndexOutOfBoundsException: the iterator is at the beginning of the ordered array list.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new IndexOutOfBoundsException("Index out of bounds: " + prevIndex); }
                previous();
                return removeNext();
            }  // Time complexity: O(n)

            /** Inserts a new value at current iterator position.
                @param value: the new value to insert into the ordered array list.
                @throws IllegalArgumentException: the new value already exists or is not inserted in order.
            */
            @Override
            public void add(T value) {
                if (prevIndex != -1 && value.compareTo(get(prevIndex)) <= 0) {
                    throw new IllegalArgumentException("New value already exists or not inserted in order: " + value);
                }
                if (nextIndex != size() &&  value.compareTo(get(nextIndex)) >= 0) {
                    throw new IllegalArgumentException("New value already exists or not inserted in order: " + value);
                }
                insert(nextIndex, value);
                next();
            }  // Time complexity: O(n)

            /** Moves the iterator to the beginning of the ordered array list. */
            @Override
            public void reset() {
                prevIndex = -1;
                nextIndex = 0;
            }  // Time complexity: O(1)
        };
    }  // Time complexity: O(1)
}