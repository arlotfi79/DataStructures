package com.company.PatientHandler;

public interface AugArrayInterface<X, Y> {

    int size( );

    /** Returns the element at index i (but does not remove) . */
    Entry<X, Y> get(int i) throws IndexOutOfBoundsException;
    /** Replaces the element at index i with e,
     * @return the replaced element. */
    Entry<X, Y> set(Entry<X, Y> entry, Y Health) throws IndexOutOfBoundsException;
    /** Inserts element e at index i */
    public Entry<X, Y>  add(Entry<X, Y> entry) throws IndexOutOfBoundsException;

    Entry<X, Y> remove(Entry<X, Y> entry) throws IndexOutOfBoundsException;
}

