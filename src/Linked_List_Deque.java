// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.NoSuchElementException;

/** A deque implemented by doubly-linked list */
public class Linked_List_Deque<T> {
    /** A doubly-linked list node */
    private final class DNode {
        // Data fields
        T data;  // The data stored in the node
        DNode next;  // A reference to the next node in the linked list
        DNode prev;  // A reference to the previous node in the linked list

        // Constructors

        DNode(T data) { this.data = data; }

        DNode(T data, DNode next, DNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    // Data fields
    private DNode front;  // A reference to the front node of the deque
    private DNode rear;  // A reference to the rear node of the deque
    private int numOfItems;  // Number of values in the deque

    // Constructors

    public Linked_List_Deque() {}  // Default constructor

    public Linked_List_Deque(Linked_List_Deque<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null deque"); }
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            front = rear = new DNode(other.front.data);
            DNode q = other.front.next;
            while (q != null) {
                rear.next = new DNode(q.data, null, rear);
                rear = rear.next;
                q = q.next;
            }
        }
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
    public final T peekFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Returns the value at the rear end of the deque.
        @return: the value at the rear end of the deque
        @throws NoSuchElementException: the deque is empty.
    */
    public final T peekLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return rear.data;
    }  // Time complexity: O(1)

    /** Removes the value at the front end of the deque.
        @return: the value removed
        @throws NoSuchElementException: the deque is empty.
    */
    public final T pollFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = front.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else {
            front = front.next;
            front.prev = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Removes the value at the rear end of the queue.
        @return: the value removed
        @throws NoSuchElementException: the deque is empty.
    */
    public final T pollLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = rear.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else {
            rear = rear.prev;
            rear.next = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Inserts a new value to the front end of the deque.
        @param value: the new value to insert to the deque
    */
    public final void offerFirst(T value) {
        if (numOfItems++ == 0) { front = rear = new DNode(value); }
        else {
            front.prev = new DNode(value, front, null);
            front = front.prev;
        }
    }  // Time complexity: O(1)

    /** Inserts a new value to the rear end of the deque.
        @param value: the new value to insert to the deque
    */
    public final void offerLast(T value) {
        if (numOfItems++ == 0) { front = rear = new DNode(value); }
        else {
            rear.next = new DNode(value, null, rear);
            rear = rear.next;
        }
    }  // Time complexity: O(1)
}