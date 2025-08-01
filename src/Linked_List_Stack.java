// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-26

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


import java.util.NoSuchElementException;

/** A stack implemented by singly-linked list */
public class Linked_List_Stack<T> {
    /** A singly-linked list node */
    private final class Node {
        // Data fields
        T data;  // The data stored in the node
        Node next;  // A reference to the next node in the list

        // Constructors

        Node(T data) { this.data = data; }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // Data fields
    private Node top;  // A reference to the top node of the stack
    private int numOfItems;  // The number of nodes in the stack

    // Constructors

    public Linked_List_Stack() {}  // Default constructor

    public Linked_List_Stack(Linked_List_Stack<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null stack"); }
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            top = new Node(other.top.data);
            Node p = top, q = other.top.next;
            while (q != null) {
                p.next = new Node(q.data);
                p = p.next;
                q = q.next;
            }
        }
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
    public final T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        return top.data;
    }  // Time complexity: O(1)

    /** Removes the value at the top of the stack.
        @return: the value removed
        @throws NoSuchElementException: the stack is empty.
    */
    public final T pop() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        T toBeRemoved = peek();
        top = top.next;
        numOfItems--;
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Inserts a new value onto the top of the stack.
        @param item: the new value to insert onto the stack
    */
    public final void push(T item) {
        top = new Node(item, top);
        numOfItems++;
    }  // Time complexity: O(1)
}