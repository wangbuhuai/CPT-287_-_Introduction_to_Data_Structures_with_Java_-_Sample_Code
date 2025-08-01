// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** A singly-linked list node */
public class Node<T> {
    // Data fields
    public T data;  // The data stored in the node
    public Node<T> next;  // A reference to the next node in the list
    
    // Constructors
    
    public Node(T data) { this.data = data; }
    
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}