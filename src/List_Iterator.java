// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** A list iterator */
public interface List_Iterator<T> extends Iterator<T> {
    /** Tests whether there exists a previous value at current iterator position.
        @return: {true} if there exists a previous value; {false} otherwise
    */
    boolean hasPrevious();
    
    /** Moves the iterator backword one position and returns the value passed by.
        @return: the value passed by during the iterator movement
    */
    T previous();
    
    /** Removes the previous value at current iterator position.
        @return: the value removed
    */
    T removePrevious();
    
    /** Updates the next value at current iterator position.
        @param value: the new value to replace the next value
    */
    void setNext(T value);
    
    /** Updates the previous value at current iterator position.
        @param value: the new value to replace the previous value
    */
    void setPrevious(T value);
    
    /** Inserts a new value at current iterator position.
        @param value: the new value to insert into the list.
    */
    void add(T value);
    
    /** Moves the iterator to the beginning of the list. */
    void reset();
}