package com.company.ArrayList;


public class ArrayList<E> implements ArrayListInterface<E> {
    // *instance variables
    public static final int CAPACITY = 16;
    private E[] data;    // generic array
    private int size = 0;

    // *Constructors
    // default capacity
    public ArrayList(){
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];    // safe casting
    }

    // *Private Methods
    // index checking
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index at: " + i);
    }

    // resize method for dynamic ArrayList
    protected void resize(int capacity){
        E[] tmp = (E[]) new Object[capacity]; // safe casting
        for (int k = 0; k < size; k++)
            tmp[k] = data[k];
        data = tmp;
    }

    // *Public Methods
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E tmp = data[i];
        data[i] = e;
        return tmp;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        // if array is full, resize with double capacity
        if (size == data.length && size <= 10e6)
            resize(2 * data.length);

        // making space to insert element
        for (int j = size - 1; j >= i; j--)
            data[j + 1] = data[j];

        data[i] = e;
        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E tmp = data[i];

        // Shift elements from i->size-1 to fill the removed index
        for (int j = i; j < size-1; j++){
            data[j] = data[j+1];
        }

        data[size-1] = null; // helping the garbage collector :)
        size--;
        return tmp;
    }

}
