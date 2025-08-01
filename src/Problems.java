// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-08-01


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problems {
    /** Tests whether a singly-linked list is circular or not.
        @param node: a reference to a node in the list
        @return: {true} if the linked list is circular; {false} otherwise
    */
    public static <T> boolean isCircular(Node<T> node) {
        Node<T> current = node;
        while (current != null) {
            current = current.next;
            if (current == node) { return true; }
        }
        return false;
    }  // Time complexity: O(n)
    
    /** Returns the value stored in the middle node of the singly-linked list.
        @param head: a reference to the head node of the linked list
        @return: the value stored in the middle node of the linked list
    */
    public static <T> T middleValue(Node<T> head) {
        Node<T> fast = head;  // Node "fast" will advance in every iteration.
        Node<T> slow = head;  // Node "slow" will advance in even iterations only.
        boolean isEven = false;
        while (fast != null) {
            fast = fast.next;
            if (isEven) { slow = slow.next; }
            isEven = !isEven;
        }
        return slow.data;
    }  // Time complexity: O(n)

    /** Reverses an array of integers.
        @param arr: the array of integers to reverse
        @throws NullPointerException: the array is null.
    */
    public static void reverse(int[] arr) {
        if (arr == null) { throw new NullPointerException("Accessing null array"); }
        Linked_List_Stack<Integer> stack = new Linked_List_Stack<>();
        for (int val : arr) { stack.push(val); }
        for (int i = 0; i < arr.length; i++) { arr[i] = stack.pop(); }
    }  // Time complexity: O(n)  Space complexity: O(n)

    /** Tests whether a string is palindromic.
        @param s: the string to test
        @return: {true} if the string is palindromic; {false} otherwise
        @throws NullPointerException: the string is null.
    */
    public static boolean isPalindromic(String s) {
        if (s == null) { throw new NullPointerException("Accessing null string"); }
        Linked_List_Stack<Character> stack = new Linked_List_Stack<>();
        for (int i = 0; i < s.length(); i++) { stack.push(s.charAt(i)); }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) { builder.append(stack.pop()); }
        return builder.toString().equals(s);
    }  // Time complexity: O(n)  Space complexity: O(n)

    /** Tests whether parentheses are balanced in an expression.
        @param exp: the expression to test
        @return: {true} if parentheses are balanced in the expression; {false} otherwise
        @throws NullPointerException: the expression is null.
    */
    public static boolean isBalanced(String exp) {
        if (exp == null) { throw new NullPointerException("Accessing null expression"); }
        Linked_List_Stack<Character> stack = new Linked_List_Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(' || exp.charAt(i) == '[' || exp.charAt(i) == '{') { stack.push(exp.charAt(i)); }
            if (exp.charAt(i) == ')' || exp.charAt(i) == ']' || exp.charAt(i) == '}') {
                if (stack.isEmpty()) { return false; }
                if (exp.charAt(i) == ')' && stack.peek() != '(') { return false; }
                if (exp.charAt(i) == ']' && stack.peek() != '[') { return false; }
                if (exp.charAt(i) == '}' && stack.peek() != '{') { return false; }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }  // Time complexity: O(n)  Space complexity: O(n)

    /** Evaluates a postfix expression.
        @param postfixExp: the postfix expression to evaluate
        @return: the evaluation result
        @throws NullPointerException: the postfix expression is null.
        @throws ArithmeticException: divisor is equal to zero.
        @throws IllegalArgumentException: operator is not supported.
    */
    public static int evaluate(String postfixExp) {
        if (postfixExp == null) { throw new NullPointerException("Accessing null expression"); }
        Linked_List_Stack<Integer> stack = new Linked_List_Stack<>();
        Scanner scanner = new Scanner(postfixExp);
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (Character.isDigit(token.charAt(0))) { stack.push(Integer.parseInt(token)); }  // Token is an operand.
            else {  // Token is an operator.
                int right = stack.pop();
                int left = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(left + right);
                        break;
                    case "-":
                        stack.push(left - right);
                        break;
                    case "*":
                        stack.push(left * right);
                        break;
                    case "/":
                        if (right == 0) { throw new ArithmeticException("Division by zero"); }
                        stack.push(left / right);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported operator: " + token);
                }
            }
        }
        scanner.close();
        return stack.peek();
    }   // Time complexity: O(n)  Space complexity: O(n)

    /** Returns the precedence of an operator.
        @param oper: the operator to return its precedence
        @return: the precedence of the operator
        @throws NullPointerException: the operator is null.
        @throws IllegalArgumentException: the operator is not supported.
    */
    private static int precedence(String oper) {
        if (oper == null) { throw new NullPointerException("Accessing null operator"); }
        if (oper.equals("*") || oper.equals("/")) { return 12; }
        else if (oper.equals("+") || oper.equals("-")) { return 11; }
        else { throw new IllegalArgumentException("Unsupported operator: " + oper); }
    }  // Time complexity: O(1)

    /** Converts an infix expression to postfix.
        @param infixExp: the infix expression to convert
        @return: the result postfix expression
        @throws NullPointerException: the infix expression is null.
    */
    public static String infixToPostfix(String infixExp) {
        if (infixExp == null) { throw new NullPointerException("Accessing null expression"); }
        Linked_List_Stack<String> stack = new Linked_List_Stack<>();
        Scanner scanner = new Scanner(infixExp);
        StringBuilder postfix = new StringBuilder();
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (Character.isDigit(token.charAt(0))) { postfix.append(' ').append(token); }  // Token is an operand.
            else if (token.equals("(")) { stack.push(token); }
            else if (token.equals(")")) {
                while (!stack.peek().equals("(")) { postfix.append(' ').append(stack.pop()); }
                stack.pop();
            } else {  // Token is an operator.
                while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(token) <= precedence(stack.peek())) {
                    postfix.append(' ').append(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) { postfix.append(' ').append(stack.pop()); }
        scanner.close();
        return postfix.substring(1);
    }  // Time complexity: O(n)  Space complexity: O(n)

    /** Finds the height of a binary tree.
        @param root: a reference to the root of a binary tree
        @return: height of the binary tree
    */
    public static <T> int height(BTNode<T> root) {
        if (root == null) { return 0; }
        return Math.max(height(root.left), height(root.right)) + 1;
    }  // Time complexity: O(n)

    /** Tests whether a binary tree is full.
        @param root: a reference to the root node of the binary tree
        @return: {true} if the binary tree is full; {false} otherwise
    */
    public static <T> boolean isFull(BTNode<T> root) {
        // Base case
        if (root == null) { return true; }
        // Count non-null children of the root.
        int count = 0;
        if (root.left != null) { count++; }
        if (root.right != null) { count++; }
        if (count == 1) { return false; }  // Not full
        // Recurrence relation
        return isFull(root.left) && isFull(root.right);
    }  // Time complexity: O(n)

    /** Preorder traverses a binary tree.
        @param root: a reference to the root of the binary tree
    */
    public static <T> void preorderTraversal(BTNode<T> root) {
        if (root != null) {
            System.out.print(root.data.toString() + ' ');
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }  // Time complexity: O(n)

    /** Inorder traverses a binary tree.
        @param root: a reference to the root of the binary tree
    */
    public static <T> void inorderTraversal(BTNode<T> root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data.toString() + ' ');
            inorderTraversal(root.right);
        }
    }  // Time complexity: O(n)

    /** Postorder traverses a binary tree.
        @param root: reference to the root of the binary tree
    */
    public static <T> void postorderTraversal(BTNode<T> root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data.toString() + ' ');
        }
    }  // Time complexity: O(n)

    /** Generates a sorted list from a binary search tree.
        @param root: a reference to the root node of the binary search tree
        @return: a sorted list generated from the binary search tree
    */
    public static <T> List<T> toSortedList(BTNode<T> root) {
        // Base case
        if (root == null) { return new ArrayList<>(); }
        // Generate a sorted list representing the left subtree.
        List<T> left = new ArrayList<>();
        if (root.left != null) { left = toSortedList(root.left); }
        // Generate a sorted list representing the right subtree.
        List<T> right = new ArrayList<>();
        if (root.right != null) { right = toSortedList(root.right); }
        // Generate the result list.
        List<T> result = new ArrayList<>(left);
        result.add(root.data);
        result.addAll(right);
        return result;
    }  // Time complexity: O(n)

    /** Generates a balanced binary search tree from a segment of a sorted list.
        @param li: a sorted list
        @param i: index of the beginning of the segment
        @param j: index of the end of the segment
        @return: a reference to the root of a BST generated from the list segment
    */
    private static <T> BTNode<T> toBST(List<T> li, int i, int j) {
        if (i > j) { return null; }  // Base case
        int mid = (i + j) / 2;  // Get the middle value.
        BTNode<T> left = toBST(li, i, mid - 1);  // Recursively generate the left subtree.
        BTNode<T> right = toBST(li, mid + 1, j);  // Recursively generate the right subtree.
        return new BTNode<T>(li.get(mid), left, right);
    }  // Time complexity: O(n)

    // Wrapper method
    public static <T> BTNode<T> toBST(List<T> li) { return toBST(li, 0, li.size() - 1); }  // Time complexity: O(n)

    /** Returns the minimum key in a binary search tree.
        @param root: a non-null reference to the root node of the binary search tree
        @return: the minimum key in the binary search tree
    */
    public static <T> T minKey(BTNode<T> root) {
        BTNode<T> p = root;
        while (p.left != null) { p = p.left; }
        return p.data;
    }  // Time complexity: O(h)

    /** Returns the maximum key in a binary search tree.
        @param root: a non-null reference to the root node of the binary search tree
        @return: the maximum key in the binary search tree
    */
    public static <T> T maxKey(BTNode<T> root) {
        BTNode<T> p = root;
        while (p.right != null) { p = p.right; }
        return p.data;
    }  // Time complexity: O(h)
}