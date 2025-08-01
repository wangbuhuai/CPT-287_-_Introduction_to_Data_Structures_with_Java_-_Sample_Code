// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-27

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-27


/** A binary tree node */
public class BTNode<T> {
    // Data fields
    public T data;  // The data stored in the node
    public BTNode<T> left;  // A reference to the left child node
    public BTNode<T> right;  // A reference to the right child node

    // Constructors

    public BTNode(T data) { this.data = data; }

    public BTNode(T data, BTNode<T> left, BTNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}