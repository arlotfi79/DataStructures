package com.company.ArrayList;

public interface ArrayListInterface<E> {
    /** @returns the current size of array*/
    int size( );

    boolean isEmpty();

    /** Returns the element at index i */
    E get(int i) throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e,
     * @return the replaced element. */
    E set(int i, E e) throws IndexOutOfBoundsException;

    /** Inserts element e at index i */
    void add(int i, E e) throws IndexOutOfBoundsException;

    /** Removes the element at index i
     * @returns the removed element*/
    E remove(int i) throws IndexOutOfBoundsException;
}
