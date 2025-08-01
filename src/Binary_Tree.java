// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-27

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-28


import java.util.NoSuchElementException;

/** A binary tree */
public class Binary_Tree<T> {
    /** A binary tree node */
    protected class BTNode {
        // Data fields
        T data;  // The data stored in the node
        BTNode left;  // A reference to the left child node
        BTNode right;  // A reference to the right child node

        // Constructors

        BTNode(T data) { this.data = data; }

        BTNode(T data, BTNode left, BTNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Data fields
    protected BTNode root;  // A reference to the root node of the binary tree

    // Constructors

    protected Binary_Tree() {}  // Default constructor

    protected Binary_Tree(Binary_Tree<T> other) {  // Copy constructor
        if (other == null) { throw new NullPointerException("Accessing null reference"); }
        root = copy(other.root);
    }

    private Binary_Tree(BTNode root) { this.root = root; }

    // Methods

    /** Creates a deep copy of a binary tree.
        @param root: a reference to the root node of the binary tree
        @return: a reference to the root node of the deep copy
    */
    private BTNode copy(BTNode root) {
        if (root == null) { return null; }
        return new BTNode(root.data, copy(root.left), copy(root.right));
    }  // Time complexity: O(n)

    /** Tests whether the binary tree is a null tree.
        @return: {true} if the binary tree is a null tree; {false} otherwise
    */
    protected final boolean isNull() { return root == null; }  // Time complexity: O(1)

    /** Tests whether the binary tree is a leaf.
        @return: {true} if the binary tree is a leaf; {false} otherwise
    */
    protected final boolean isLeaf() {
        if (isNull()) { return true; }
        return root.left == null && root.right == null;
    }  // Time complexity: O(1)

    /** Returns the data in the root.
        @return: the data in the root
        @throws NoSuchElementException: the binary tree is a null tree.
    */
    protected final T get() {
        if (isNull()) { throw new NoSuchElementException("Accessing null tree"); }
        return root.data;
    }  // Time complexity: O(1)

    /** Returns a shallow copy of the left subtree.
        @return: a shallow copy of the left subtree
        @throws NullPointerException: the binary tree is a null tree.
    */
    protected final Binary_Tree<T> getLeftSubtree() {
        if (isNull()) { throw new NullPointerException("Accessing null tree"); }
        return new Binary_Tree<>(root.left);
    }  // Time complexity: O(1)

    /** Returns a shallow copy of the right subtree.
        @return: a shallow copy of the right subtree
        @throws NullPointerException: the binary tree is a null tree.
    */
    protected final Binary_Tree<T> getRightSubtree() {
        if (isNull()) { throw new NullPointerException("Accessing null tree"); }
        return new Binary_Tree<>(root.right);
    }  // Time complexity: O(1)

    /** Customizes the output format for the binary tree.
        @return: a string representing the output format of the binary tree
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Linked_List_Queue<BTNode> queue = new Linked_List_Queue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BTNode node = queue.poll();
                if (node == null) { builder.append("null").append(' '); }
                else {
                    builder.append(node.data).append(' ');
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            builder.deleteCharAt(builder.length() - 1).append('\n');
        }
        return builder.toString();
    }  // Time complexity: O(n)
}