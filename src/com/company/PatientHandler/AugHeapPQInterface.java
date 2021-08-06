package com.company.PatientHandler;

public interface AugHeapPQInterface<X, Y> {
    void replaceHealth(Entry<X, Y> entry, Y Health) throws IllegalArgumentException;
    void remove(Entry<X, Y> entry) throws IllegalArgumentException;
}
