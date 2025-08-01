// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.NoSuchElementException;

/** A doubly-linked list with iterator */
public class Linked_List<T> implements Iterable<T> {
    /** A doubly-linked list node */
    protected final class DNode {
        // Data fields
        T data;  // The data stored in the node
        DNode next;  // A reference to the next node in the list
        DNode prev;  // A reference to the previous node in the list
        
        // Constructors
        
        DNode(T data) { this.data = data; }
        
        DNode(T data, DNode next, DNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    
    // Data fields

    protected DNode head;  // A reference to the head node of the linked list.
    protected DNode tail;  // A reference to the tail node of the linked list.
    protected int numOfItems;  // The size of the linked list
    
    // Constructors
    
    public Linked_List() {}  // Default constructor
    
    public Linked_List(Linked_List<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null linked list"); }
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            head = tail = new DNode(other.head.data);
            DNode q = other.head.next;
            while (q != null) {
                tail.next = new DNode(q.data, null, tail);
                tail = tail.next;
                q = q.next; 
            }
        }
    }
    
    // Methods
    
    /** Returns the size of the linked list.
        @return: the size of the linked list
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)
    
    /** Tests whether the linked list is empty or not.
        @return: {true} if the linked list is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)
    
    /** Returns the item stored in the head node of the linked list.
        @return: the item stored in the head node of the linked list
        @throws NoSuchElementException: the linked list is empty.
    */
    public final T getFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty linked list"); }
        return head.data;
    }  // Time complexity: O(1)
    
    /** Returns the item stored in the tail node of the linked list.
        @return: the item stored in the tail node of the linked list
        @throws NoSuchElementException: the linked list is empty.
    */
    public final T getLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty linked list"); }
        return tail.data;
    }  // Time complexity: O(1)
    
    /** Inserts a new value to the front end of the linked list.
        @param value: the new value to insert into the linked list
    */
    public void addFirst(T value) {
        if (numOfItems++ == 0) { head = tail = new DNode(value); }
        else {
            head.prev = new DNode(value, head, null);
            head = head.prev;
        }
    }  // Time complexity: O(1)
    
    /** Inserts a new value to the rear end of the linked list.
        @param value: the new value to insert into the linked list
    */
    public void addLast(T value) {
        if (numOfItems++ == 0) { head = tail = new DNode(value); }
        else {
            tail.next = new DNode(value, null, tail);
            tail = tail.next;
        }
    }  // Time complexity: O(1)
    
    /** Removes an item from the front end of the linked list.
        @return: the item removed
        @throws NoSuchElementException: the linked list is empty.
    */
    public final T removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty linked list"); }
        T toBeRemoved = head.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            head = head.next;
            head.prev = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)
    
    /** Removes an item from the rear end of the linked list.
        @return: the item removed
        @throws NoSuchElementException: the linked list is empty.
    */
    public final T removeLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty linked list"); }
        T toBeRemoved = tail.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)
    
    /** Tests whether a target value appears in the linked list or not.
        @param target: the target value to search in the linked list
        @return: {true} if the target value appears in the linked list; {false} otherwise
    */
    public final boolean contains(T target) {
        DNode p = head;
        while (p != null) {
            if (p.data.equals(target)) { return true; }
            p = p.next;
        }
        return false;
    }  // Time complexity: O(n)
    
    /** Removes all the items from the linked list. */
    public final void clear() {
        head = tail = null;
        numOfItems = 0;
    }  // Time complexity: O(1)
    
    /** Customizes the output format for the linked list.
        @return: a string representing the output format of the linked list.
    */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        DNode p = head;
        while (p != null) {
            builder.append(p.data);
            if (p.next != null) { builder.append(" -> "); }
            p = p.next;
        }
        return builder.append(']').toString();
    }  // Time complexity: O(n)

    /** Generates a list iterator positioned at the beginning of the linked list.
        @return: a list iterator positioned at the beginning of the linked list
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
                @throws NoSuchElementException: the iterator is at the end of the linked list.
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
                @throws NoSuchElementException: the iterator is at the beginning of the linked list.
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
                @throws NoSuchElementException: the iterator is at the end of the linked list.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                nextNode.data = value;
                next();
            }  // Time complexity: O(1)

            /** Updates the previous value at current iterator position.
                @param value: the new value to replace the previous value
                @throws NoSuchElementException: the iterator is at the beginning of the linked list.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null reference"); }
                prevNode.data = value;
                previous();
            }  // Time complexity: O(1)

            /** Removes the next value at current iterator position.
                @return: the value removed
                @throws NoSuchElementException: the iterator is at the end of the linked list.
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
                @throws NoSuchElementException: the iterator is at the beginning of the linked list.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null reference"); }
                previous();
                return removeNext();
            }  // Time complexity: O(1)

            /** Inserts a new value at current iterator position.
                @param value: the new value to insert into the linked list.
            */
            @Override
            public void add(T value) {
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

            /** Moves the iterator to the beginning of the linked list. */
            @Override
            public void reset() {
                prevNode = null;
                nextNode = head;
            }  // Time complexity: O(1)
        };
    }  // Time complexity: O(1)
}