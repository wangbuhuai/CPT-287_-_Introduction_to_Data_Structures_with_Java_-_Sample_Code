// Created by Dayu Wang (dwang@stchas.edu) on 2025-06-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2025-06-24


/** An iterable feature */
@FunctionalInterface
public interface Iterable<T> {
    /** Generates an iterator positioned at the beginning of the data structure.
        @return: an iterator positioned at the beginning of the data structure
    */
    Iterator<T> iterator();
}