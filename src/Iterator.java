// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** A general iterator */
public interface Iterator<T> {
    /** Tests whether there exists a next value at current iterator position.
        @return: {true} if there exists a next value; {false} otherwise
    */
    boolean hasNext();
    
    /** Moves the iterator forward one position and returns the value passed by.
        @return: the value passed by during the iterator movement
    */
    T next();
    
    /** Removes the next value at current iterator position.
        @return: the value removed
    */
    T removeNext();
}