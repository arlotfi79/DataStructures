package com.company.FinalSystem;

import com.company.PatientHandler.AugArray;
import com.company.PatientHandler.AugHeapPQ;
import com.company.PatientHandler.Entry;
import com.company.PatientHandler.ProbeHashMap;

public class Final<X, Y> implements FinalInterface<X, Y> {

    AugHeapPQ<X, Y> heap = new AugHeapPQ<>();
    AugArray<X, Y> array = new AugArray<>();
    ProbeHashMap<X, Y> hashMap = new ProbeHashMap<>(1000000);

    @Override
    public Entry<X, Y> add(X ID, Y Health) throws IllegalArgumentException {
        Entry<X, Y> newest = heap.add(ID, Health);
        array.add(newest);
        hashMap.add(newest);

        return newest;
    }

    @Override
    public Entry<X, Y> serveFirst() {
        Entry<X, Y> rem = array.remove(0);
        heap.remove(rem);
        hashMap.remove(rem.getID());

        return rem;
    }

    @Override
    public Entry<X, Y> serveSickest() {
        Entry<X, Y> rem = heap.removeMin();
        array.remove(rem);
        hashMap.remove(rem.getID());

        return rem;
    }

    @Override
    public Entry<X, Y> update(X ID, Y Health) throws IllegalArgumentException {
        Entry<X, Y> up = hashMap.get(ID);
        if (up == null)
            throw new IllegalArgumentException("Entry Not Found!!");
        heap.replaceHealth(up, Health);

        return up;
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }
}
