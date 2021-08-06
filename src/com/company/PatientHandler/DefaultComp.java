package com.company.PatientHandler;

import java.util.Comparator;

public class DefaultComp<Y> implements Comparator<Y> {
    @Override
    public int compare(Y o1, Y o2) throws ClassCastException {
        return ((Comparable<Y>) o1).compareTo(o2);
    }
}
