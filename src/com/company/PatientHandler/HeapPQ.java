package com.company.PatientHandler;

import com.company.ArrayList.ArrayList;
import java.util.Comparator;

public class HeapPQ<X, Y> extends BasePQ<X, Y> {

    protected ArrayList<Entry<X, Y>> heap = new ArrayList<>();

    public HeapPQ() {
        super();
    }

    public HeapPQ(Comparator<Y> comp) {
        super(comp);
    }

    // *Heap utilities
    protected int parent(int j) {
        return (j-1) /2;
    }

    protected int left(int j) {
        return 2*j + 1;
    }

    protected int right(int j) {
        return 2*j + 2;
    }

    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    // * Heap balancing utilities
    protected void swap(int i, int j) {
        Entry<X, Y> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // upHeap after Insertion
    protected void upHeap(int j) {
        while (j > 0) { // continue until reaching Root
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0)
                break; // heap property matched
            swap(j, p);
            j = p;  // continue from the parent
        }
    }

    // downHeap after Deletion
    protected void downHeap(int j) {
        while (hasLeft(j)) {    // continue to bottom
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;    // right child may be smaller

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;   // right child is smaller
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;  // heap property matched
            swap(j, smallChildIndex);
            j = smallChildIndex;    // continue from child
        }
    }

    // *general utilities
    public int size() {
        return heap.size();
    }

    public Entry<X, Y> min() {
        if (heap.isEmpty())
            return null;
        return heap.get(0);
    }

    /** Inserts ID-Health pair
     * @returns the entry created.*/
    public Entry<X, Y> add(X ID, Y Health) throws IllegalArgumentException {
        checkHealthComparability(Health);
        Entry<X, Y> newest = new PriorityQueueEntry<>(ID, Health);
        heap.add(heap.size(), newest);   // add to the end
        upHeap(heap.size() - 1); // fix if heap property is not matched

        return newest;
    }

    /** remove minimum
     * @returns the Entry*/
    public Entry<X, Y> removeMin() {
        if (heap.isEmpty())
            return null;

        Entry<X, Y> answer = heap.get(0);
        swap(0, heap.size() - 1);   // swap minimum with the last node
        heap.remove(heap.size() - 1);   // now delete it
        downHeap(0);    // fix if heap property is not matched

        return answer;
    }

}
