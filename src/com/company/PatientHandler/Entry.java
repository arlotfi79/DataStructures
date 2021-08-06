package com.company.PatientHandler;

// using the Composition Design pattern for more convenient access
public interface Entry<X, Y> {
    X getID();
    Y getHealth();
}
