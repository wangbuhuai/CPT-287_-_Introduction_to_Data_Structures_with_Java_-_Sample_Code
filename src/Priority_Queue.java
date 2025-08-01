// Created by Dayu Wang (dwang@stchas.edu) on 2025-08-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-08-01


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/** A min-heap priority queue */
public class Priority_Queue<T extends Comparable<T>> {
    // Data fields
    private final List<T> heap;

    // Constructors

    public Priority_Queue() { heap = new ArrayList<>(); }  // Default constructor

    public Priority_Queue(Priority_Queue<T> other) { heap = new ArrayList<>(other.heap); }  // Copy constructor

    // Methods

    /** Returns the size of the priority queue.
        @return: the size of the priority queue
    */
    public final int size() { return heap.size(); }  // Time complexity: O(1)

    /** Tests whether the priority queue is empty.
        @return: {true} if the priority queue is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value with the highest priority.
        @return: value with the highest priority
        @throws NoSuchElementException: the priority queue is empty.
    */
    public T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return heap.get(0);
    }  // Time complexity: O(1)

    /** Removes and returns the value with the highest priority.
        @return: value removed
        @throws NoSuchElementException: the priority queue is empty.
    */
    public T poll() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        T toBeRemoved = heap.get(0);
        // Replace the value at index 0 with the last value in the array.
        heap.set(0, heap.get(heap.size() - 1));
        // Remove the last value from the array.
        heap.remove(heap.size() - 1);
        // Find the 2 children of the root.
        int parent = 0, left = 1, right = 2, min = parent;
        while (true) {
            // Find the min value among parent, left, and right.
            if (left < heap.size() && heap.get(left).compareTo(heap.get(min)) < 0) { min = left; }
            if (right < heap.size() && heap.get(right).compareTo(heap.get(min)) < 0) { min = right; }
            // If the parent is the minimum, then stop further processing.
            if (min == parent) { break; }
            // Swap the parent with the minimum child.
            Collections.swap(heap, parent, min);
            // Set up for the next iteration.
            parent = min;
            left = 2 * parent + 1;
            right = 2 * parent + 2;
        }
        return toBeRemoved;
    }  // Time complexity: O(log(n))

    /** Inserts a new element to the priority queue.
        @param value: element to add to the queue
    */
    public void offer(T value) {
        // Add the new element to the end of the heap.
        heap.add(value);
        int child = heap.size() - 1;
        while (child != 0) {
            // Find the parent.
            int parent = (child - 1) / 2;
            // If the parent is not greater than the child, stop further processing.
            if (heap.get(parent).compareTo(heap.get(child)) <= 0) { break; }
            // Swap the child with its parent.
            Collections.swap(heap, parent, child);
            // Set up for the next iteration.
            child = parent;
        }
    }  // Time complexity: O(log(n))
}