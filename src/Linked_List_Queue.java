// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.NoSuchElementException;

/** A queue implemented by singly-linked list */
public class Linked_List_Queue<T> {
    /** A singly-linked list node */
    private final class Node {
        // Data fields
        T data;  // The data stored in the node
        Node next;  // A reference to the next node in the linked list

        // Constructors

        Node(T data) { this.data = data; }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // Data fields
    private Node front;  // A reference to the front node of the queue
    private Node rear;  // A reference to the rear node of the queue
    private int numOfItems;  // Number of values in the queue

    // Constructors

    public Linked_List_Queue() {}  // Default constructor

    public Linked_List_Queue(Linked_List_Queue<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null queue."); }
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            front = rear = new Node(other.front.data);
            Node q = other.front.next;
            while (q != null) {
                rear.next = new Node(q.data);
                rear = rear.next;
                q = q.next;
            }
        }
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
    public final T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Removes the value at the front end of the queue.
        @return: the value removed
        @throws NoSuchElementException: the queue is empty.
    */
    public final T poll() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        T toBeRemoved = front.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else { front = front.next; }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Inserts a new value to the rear end of the queue.
        @param value: the new value to insert to the queue
    */
    public final void offer(T value) {
        if (numOfItems++ == 0) { front = rear = new Node(value); }
        else {
            rear.next = new Node(value);
            rear = rear.next;
        }
    }  // Time complexity: O(1)
}