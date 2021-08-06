package com.company.PatientHandler;

import java.util.Random;

public abstract class AbstractHashMap<X, Y> implements HashMapInterface<X, Y> {
    protected int size = 0;    // number of entries in the dictionary
    protected int capacity; // length of the table
    private int prime;  // prime factor
    private long scale, shift;  // the shift and scaling factors

    public AbstractHashMap(int capacity, int prime) {
        this.prime = prime;
        this.capacity = capacity;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    // * Constructors
    public AbstractHashMap(int capacity) {
        this(capacity, 109345121);   // default prime
    }
    public AbstractHashMap() {
        this(17);   // default capacity
    }

    // * HashFunction
     /** hashCode is part of Object Class
     @returns 32-bit integer (Using Memory Address)*/
    private int hashValue(X ID) {
        return (int) ((Math.abs(ID.hashCode()*scale + shift) % prime) % capacity);
    }

    // * public methods
    public int size( ) {
        return size;
    }
    public Entry<X, Y> get(X ID) {
        return getEntry(hashValue(ID), ID);
    }
    public Entry<X, Y> remove(X ID) {
        return removeEntry(hashValue(ID), ID);
    }
    public Entry<X, Y> add(Entry<X, Y> entry) {
        return addEntry(hashValue(entry.getID()), entry);
    }

    // * protected abstract methods to be implemented by subclasses (In this case we use Probing)
    protected abstract void createTable();
    protected abstract Entry<X, Y> getEntry(int h, X ID);
    protected abstract Entry<X, Y> addEntry(int h, Entry<X, Y> entry);
    protected abstract Entry<X, Y> removeEntry(int h, X ID);
}

