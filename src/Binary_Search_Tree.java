// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-27

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-28


import java.util.NoSuchElementException;

/** A binary search tree */
public class Binary_Search_Tree<T extends Comparable<T>> extends Binary_Tree<T> implements Iterable<T> {
    // Constructors

    public Binary_Search_Tree() { super(); }  // Default constructor

    public Binary_Search_Tree(Binary_Search_Tree<T> other) { super(other); }  // Copy constructor

    // Methods

    /** Tests whether a target value appears in a binary search tree.
        @param localRoot: a reference to the root node of the binary search tree
        @param target: the target value to search in the binary search tree
        @return: {true} if the target value appears in the binary search tree; {false} otherwise
    */
    private boolean contains(BTNode localRoot, T target) {
        if (localRoot == null) { return false; }
        if (target.compareTo(localRoot.data) < 0) { return contains(localRoot.left, target); }
        else if (target.compareTo(localRoot.data) > 0) { return contains(localRoot.right, target); }
        else { return true; }
    }  // Time complexity: O(h)

    /** Tests whether a target value appears in the binary search tree.
        @param target: the target value to search in the binary search tree
        @return: {true} if the target value appears in the binary search tree; {false} otherwise
    */
    public final boolean contains(T target) { return contains(root, target); }  // Time complexity: O(h)

    /** Inserts a new value into a binary search tree.
        @param parent: a reference to the parent node of the local root
        @param localRoot: a reference to the local root node
        @param isLeft: {true} if local root is the left child of parent; {false} if it is the right child
        @param value: the new value to insert into the binary search tree
        @return: {true} if the new value is inserted; {false} if the new value already exists in the binary search tree.
    */
    private boolean insert(BTNode parent, BTNode localRoot, boolean isLeft, T value) {
        if (localRoot == null) {
            if (isLeft) { parent.left = new BTNode(value); }
            else { parent.right = new BTNode(value); }
            return true;
        }
        if (value.compareTo(localRoot.data) < 0) { return insert(localRoot, localRoot.left, true, value); }
        else if (value.compareTo(localRoot.data) > 0) { return insert(localRoot, localRoot.right, false, value); }
        else { return false; }
    }  // Time complexity: O(h)

    /** Inserts a new value into the binary search tree
        @param value: the new value to insert into the binary search tree
        @return: {true} if the new value is inserted; {false} if the new value already exists in the binary search tree.
    */
    public final boolean insert(T value) {
        if (isNull()) {
            root = new BTNode(value);
            return true;
        }
        if (value.compareTo(get()) < 0) { return insert(root, root.left, true, value); }
        else if (value.compareTo(get()) > 0) { return insert(root, root.right, false, value); }
        else { return false; }
    }  // Time complexity: O(h)

    /** Finds the inorder successor node to replace a node.
        @param oldParent: a reference to the parent node of the node to replace
        @param oldRoot: a reference to the node to replace
        @param isLeft: {true} if old root is the left child of old parent; {false} if it is the right child
        @param localParent: a reference to the parent node of the local root node
        @param localRoot: a reference to the local root node
        @throws NullPointerException: local root node is null.
    */
    private void replaceParent(BTNode oldParent, BTNode oldRoot, boolean isLeft, BTNode localParent, BTNode localRoot) {
        if (localRoot == null) { throw new NullPointerException("Accessing null node"); }
        if (localRoot.right != null) { replaceParent(oldParent, oldRoot, isLeft, localRoot, localRoot.right); }
        else {
            if (isLeft) { oldParent.left = localRoot; }
            else { oldParent.right = localRoot; }
            if (localParent != oldRoot) {
                localParent.right = localRoot.left;
                localRoot.left = oldRoot.left;
            }
            localRoot.right = oldRoot.right;
        }
    }  // Time complexity: O(h)

    /** Removes a value from a binary search tree.
        @param parent: a reference to the parent node of the local root
        @param localRoot: a reference to the local root node
        @param isLeft: {true} if local root is the left child of parent; {false} if it is the right child
        @param value: the value to remove from the binary search tree
        @return: {true} if the value is removed; {false} if the value does not exist in the binary search tree.
    */
    private boolean remove(BTNode parent, BTNode localRoot, boolean isLeft, T value) {
        if (localRoot == null) { return false; }
        if (value.compareTo(localRoot.data) < 0) { return remove(localRoot, localRoot.left, true, value); }
        else if (value.compareTo(localRoot.data) > 0) { return remove(localRoot, localRoot.right, false, value); }
        else {
            if (localRoot.left == null) {
                if (isLeft) { parent.left = localRoot.right; }
                else { parent.right = localRoot.right; }
            } else if (localRoot.right == null) {
                if (isLeft) { parent.left = localRoot.left; }
                else { parent.right = localRoot.left; }
            } else { replaceParent(parent, localRoot, isLeft, localRoot, localRoot.left); }
            return true;
        }
    }  // Time complexity: O(h)

    /** Removes a value from the binary search tree.
        @param value: the value to remove from the binary search tree
        @return: {true} if the value is removed; {false} if the value does not exist in the binary search tree.
    */
    public final boolean remove(T value) {
        if (isNull()) { return false; }
        if (value.compareTo(get()) < 0) { return remove(root, root.left, true, value); }
        else if (value.compareTo(get()) > 0) { return remove(root, root.right, false, value); }
        else {
            if (root.left == null) { root = root.right; }
            else if (root.right == null) { root = root.left; }
            else {
                BTNode parent = root, localRoot = root.left;
                while (localRoot.right != null) {
                    parent = localRoot;
                    localRoot = localRoot.right;
                }
                parent.right = localRoot.left;
                localRoot.left = root.left;
                localRoot.right = root.right;
                root = localRoot;
            }
            return true;
        }
    }  // Time complexity: O(h)

    /** Generates an iterator positioned at the beginning of the binary search tree.
        @return: an iterator positioned at the beginning of the binary search tree
    */
    public final Iterator<T> iterator() {
        return new Iterator<T>() {
            // Data field
            private final Linked_List_Stack<BTNode> stack = initialize();  // Nodes along the path

            // Methods

            /** Initializes the stack for the binary search tree iterator.
                @return: the initial stack for the binary search tree iterator
            */
            private Linked_List_Stack<BTNode> initialize() {
                Linked_List_Stack<BTNode> stack = new Linked_List_Stack<>();
                pushAllLeft(root, stack);
                return stack;
            }  // Time complexity: O(h)

            /** Pushes all the left nodes onto a stack.
                @param node: the initial binary search tree node
                @param stack: the stack to store the nodes
            */
            private void pushAllLeft(BTNode node, Linked_List_Stack<BTNode> stack) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }  // Time complexity: O(h)

            /** Tests whether there exists a next value in the binary search tree.
                @return: {true} if there exists a next value; {false} if the iterator is at the end of the tree
            */
            @Override
            public boolean hasNext() { return !stack.isEmpty(); }  // Time complexity: O(1)

            /** Moves the iterator forward one position and returns the value passed by.
                @return: the value passed by during the iterator movement
                @throws NoSuchElementException: the iterator is at the end of the binary search tree.
            */
            @Override
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                BTNode node = stack.pop();
                if (node.right != null) { pushAllLeft(node.right, stack); }
                return node.data;
            }  // Time complexity: O(h)

            /** Removes the next value at iterator position.
                @return: the value removed
                @throws NoSuchElementException: the iterator is at the end of the binary search tree.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null reference"); }
                BTNode node = stack.pop();
                if (node.right != null) { pushAllLeft(node.right, stack); }
                remove(node.data);
                return node.data;
            }  // Time complexity: O(h)
        };
    }  // Time complexity: O(1)
}