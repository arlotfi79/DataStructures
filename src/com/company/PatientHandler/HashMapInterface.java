package com.company.PatientHandler;

public interface HashMapInterface<X, Y> {
    int size();
    Entry<X, Y> get(X ID);
    Entry<X, Y> add(Entry<X, Y> entry);
    Entry<X, Y> remove(X ID);
}
