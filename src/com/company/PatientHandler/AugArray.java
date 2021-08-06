package com.company.PatientHandler;

public class AugArray<X, Y> implements AugArrayInterface<X, Y> {
    //?---------------- nested AugmentedArrayEntry class ----------------

    // *instance variables
    public static final int CAPACITY = 16;    // default capacity
    private Entry<X, Y>[] data;              // generic array
    private int size = 0;                   // current size

    // *Constructors
    public AugArray(){
        this(CAPACITY);
    }
    public AugArray(int capacity) {
        data = (Entry<X, Y>[]) new Entry[capacity];    // safe cast
    }

    // *Protected  Methods
    // index checking
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index at: " + i);
    }

    // resize method for dynamic ArrayList
    protected void resize(int capacity){
        Entry<X, Y>[] tmp = (Entry<X, Y>[]) new Entry[capacity]; // safe cast
        for (int k = 0; k < size; k++)
            tmp[k] = data[k];
        data = tmp;
    }

    protected void swapIndex(Entry<X, Y> data1, Entry<X, Y> data2) {
        int tmp = ((AugHeapPQ.AugmentedHeapPQEntry<X, Y>) data1).getArrayIndex();
        int tmp2 = ((AugHeapPQ.AugmentedHeapPQEntry<X, Y>) data2).getArrayIndex();

        ((AugHeapPQ.AugmentedHeapPQEntry<X, Y>) data1).setArrayIndex(tmp2);
        ((AugHeapPQ.AugmentedHeapPQEntry<X, Y>) data2).setArrayIndex(tmp);
    }

    // *Public  Methods
    @Override
    public int size() {
        return size;
    }

    @Override
    public Entry<X, Y> get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public Entry<X, Y> set(Entry<X, Y> entry, Y Health) throws IndexOutOfBoundsException {
        AugHeapPQ.AugmentedHeapPQEntry<X, Y> locator = (AugHeapPQ.AugmentedHeapPQEntry<X, Y>)entry;
        locator.setHealth(Health);

        return locator;
    }

    @Override
    public Entry<X, Y> add(Entry<X, Y> entry) throws IndexOutOfBoundsException {
        // if array is full, resize to double
        if (size == data.length && size <= 10e6)
            resize(2 * data.length);

        AugHeapPQ.AugmentedHeapPQEntry<X, Y> newest = (AugHeapPQ.AugmentedHeapPQEntry<X, Y>) entry;
        newest.setArrayIndex(size);
        data[size] = newest;

        size++;

        return newest;
    }

    @Override
    public Entry<X, Y> remove(Entry<X, Y> entry) throws IndexOutOfBoundsException {
        AugHeapPQ.AugmentedHeapPQEntry<X, Y> locator = (AugHeapPQ.AugmentedHeapPQEntry<X, Y>)entry;
        int aIndex = locator.getArrayIndex();

        // Shift elements from i->size-1 to fill the removed index
        for (int j = aIndex; j < size-1; j++){
            data[j] = data[j+1];
            swapIndex(data[j], data[j+1]);
        }

        data[size-1] = null; // help garbage collector
        size--;
        return locator;
    }

    public Entry<X, Y> remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        Entry<X, Y> tmp = data[i];

        // Shift elements from i->size-1 to fill the removed index
        for (int j = i; j < size-1; j++){
            data[j] = data[j+1];
            swapIndex(data[j], data[j+1]);
        }

        data[size-1] = null; // help garbage collector
        size--;
        return tmp;
    }
}
