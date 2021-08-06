package com.company.FinalSystem;

import com.company.PatientHandler.Entry;

public interface FinalInterface<X, Y> {
    Entry<X, Y> add(X ID, Y Health) throws IllegalArgumentException;
    Entry<X, Y> serveFirst();
    Entry<X, Y> serveSickest();
    Entry<X, Y> update(X ID, Y Health) throws IllegalArgumentException;
}
