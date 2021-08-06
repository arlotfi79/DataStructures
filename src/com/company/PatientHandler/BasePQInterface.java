package com.company.PatientHandler;

public interface BasePQInterface<X, Y> {
    int size();
    boolean isEmpty();
    Entry<X, Y> add(X ID, Y Health) throws IllegalArgumentException;
    Entry<X, Y> min();
    Entry<X, Y> removeMin();
}
