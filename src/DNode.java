// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** A doubly-linked list node */
public class DNode<T> {
    // Data fields
    public T data;  // The data stored in the node
    public DNode<T> next;  // A reference to the next node in the list
    public DNode<T> prev;  // A reference to the previous node in the list
    
    // Constructors
    
    public DNode(T data) { this.data = data; }
    
    public DNode(T data, DNode<T> next, DNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}