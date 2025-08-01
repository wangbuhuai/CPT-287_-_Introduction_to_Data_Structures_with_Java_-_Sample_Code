// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-22

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-25


import java.util.NoSuchElementException;

/** An ordered doubly-linked list with iterator */
public class Ordered_Linked_List<T extends Comparable<T>> extends Linked_List<T> {
    // Constructors

    public Ordered_Linked_List() { super(); }  // Default constructor

    public Ordered_Linked_List(Ordered_Linked_List<T> other) { super(other); }  // Copy constructor

    // Methods

    /** Inserts a new value to the front end of the ordered linked list.
        @param value: the new value to insert into the ordered linked list
        @throws IllegalArgumentException: the new value is greater than the value at the front end of the linked list.
    */
    @Override
    public void addFirst(T value) {
        if (isEmpty() || value.compareTo(getFirst()) <= 0) { super.addFirst(value); }
        else { throw new IllegalArgumentException("New item is not inserted in order: " + value); }
    }  // Time complexity: O(1)

    /** Inserts a new value to the rear end of the ordered linked list.
        @param value: the new value to insert into the ordered linked list
        @throws IllegalArgumentException: the new value is less than the value at the rear end of the linked list.
    */
    @Override
    public void addLast(T value) {
        if (isEmpty() || value.compareTo(getLast()) >= 0) { super.addLast(value); }
        else { throw new IllegalArgumentException("New item is not inserted in order: " + value); }
    }

    /** Inserts a new value into the ordered linked list.
        @param value: the new value to insert to the linked list
    */
    public void insert(T value) {
        DNode p = head;
        while (p != null && p.data.compareTo(value) < 0) { p = p.next; }
        if (p == head) { super.addFirst(value); }
        else if (p == null) { super.addLast(value); }
        else {
            DNode newNode = new DNode(value, p, p.prev);
            newNode.next.prev = newNode;
            newNode.prev.next = newNode;
            numOfItems++;
        }
    }  // Time complexity: O(n)

    /** Generates a list iterator positioned at the beginning of the ordered linked list.
        @return: a list iterator positioned at the beginning of the ordered linked list
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private DNode nextNode = head;  // A reference to the next node at current iterator position
            private DNode prevNode = null;  // A reference to the previous node at current iterator position

            /** Tests whether there exists a next value at current iterator position.
                @return: {true} if there exists a next value; {false} otherwise
            */
            @Override
            public boolean hasNext() { return nextNode != null; }  // Time complexity: O(1)

            /** Tests whether there exists a previous value at current iterator position.
                @return: {true} if there exists a previous value; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return prevNode != null; }  // Time complexity: O(1)

            /** Moves the iterator forward one position and returns the value passed by.
                @return: the value passed by during the iterator movement
                @throws NoSuchElementException : the iterator is at the end of the ordered linked list.
            */
            @Override
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                prevNode = nextNode;
                nextNode = nextNode.next;
                return prevNode.data;
            }  // Time complexity: O(1)

            /** Moves the iterator backword one position and returns the value passed by.
                @return: the value passed by during the iterator movement
                @throws NoSuchElementException: the iterator is at the beginning of the ordered linked list.
            */
            @Override
            public T previous() {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null reference"); }
                nextNode = prevNode;
                prevNode = prevNode.prev;
                return nextNode.data;
            }  // Time complexity: O(1)

            /** Updates the next value at current iterator position.
                @param value: the new value to replace the next value
                @throws NoSuchElementException: the iterator is at the end of the ordered linked list.
                @throws IllegalArgumentException: the replaced value is not in order.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                if (prevNode != null && value.compareTo(prevNode.data) < 0) {
                    throw new IllegalArgumentException("New value is not in order: " + value);
                }
                if (nextNode.next != null && value.compareTo(nextNode.next.data) > 0) {
                    throw new IllegalArgumentException("New value is not in order: " + value);
                }
                nextNode.data = value;
                next();
            }  // Time complexity: O(1)

            /** Updates the previous value at current iterator position.
                @param value: the new value to replace the previous value
                @throws NoSuchElementException: the iterator is at the beginning of the ordered linked list.
                @throws IllegalArgumentException: the replaced value is not in order.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null reference"); }
                if (nextNode != null && value.compareTo(nextNode.data) > 0) {
                    throw new IllegalArgumentException("New value is not in order: " + value);
                }
                if (prevNode.prev != null && value.compareTo(prevNode.prev.data) < 0) {
                    throw new IllegalArgumentException("New value is not in order: " + value);
                }
                prevNode.data = value;
                previous();
            }  // Time complexity: O(1)

            /** Removes the next value at current iterator position.
                @return: the value removed
                @throws NoSuchElementException: the iterator is at the end of the ordered linked list.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                T toBeRemoved = nextNode.data;
                if (prevNode == null) {  // The head will be removed.
                    removeFirst();
                    nextNode = head;
                } else if (nextNode == tail) {  // The tail will be removed.
                    removeLast();
                    nextNode = null;
                } else {
                    prevNode.next = nextNode.next;
                    prevNode.next.prev = prevNode;
                    nextNode = prevNode.next;
                    numOfItems--;
                }
                return toBeRemoved;
            }  // Time complexity: O(1)

            /** Removes the previous value at current iterator position.
                @return: the value removed
                @throws NoSuchElementException: the iterator is at the beginning of the ordered linked list.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null reference"); }
                previous();
                return removeNext();
            }  // Time complexity: O(1)

            /** Inserts a new value at current iterator position.
                @param value: the new value to insert into the ordered linked list.
                @throws IllegalArgumentException: the new value is not inserted in order.
            */
            @Override
            public void add(T value) {
                if (prevNode != null && value.compareTo(prevNode.data) < 0) {
                    throw new IllegalArgumentException("New value is not inserted in order: " + value);
                }
                if (nextNode != null && value.compareTo(nextNode.data) > 0) {
                    throw new IllegalArgumentException("New value is not inserted in order: " + value);
                }
                if (isEmpty()) {
                    head = tail = prevNode = new DNode(value);
                    numOfItems++;
                } else if (prevNode == null) {  // Insert to the front end.
                    addFirst(value);
                    prevNode = head;
                } else if (nextNode == null) {  // Insert to the rear end.
                    addLast(value);
                    prevNode = tail;
                } else {
                    DNode newNode = new DNode(value, nextNode, prevNode);
                    newNode.next.prev = newNode;
                    newNode.prev.next = newNode;
                    prevNode = newNode;
                    numOfItems++;
                }
            }  // Time complexity: O(1)

            /** Moves the iterator to the beginning of the ordered linked list. */
            @Override
            public void reset() {
                prevNode = null;
                nextNode = head;
            }  // Time complexity: O(1)
        };
    }  // Time complexity: O(1)
}