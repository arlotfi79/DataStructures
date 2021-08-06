package com.company.PatientHandler;

import java.util.Comparator;

public class AugHeapPQ<X, Y> extends HeapPQ<X, Y>
                                                implements AugHeapPQInterface<X, Y> {
    //?---------------- nested AugmentedHeapPriorityQueueEntry class ----------------

    protected static class AugmentedHeapPQEntry<X, Y> extends PriorityQueueEntry<X, Y> {
        private int heapIndex;
        private int arrayIndex;

        // Location Aware heap
        public AugmentedHeapPQEntry(X ID, Y Health, int heapIndex, int arrayIndex) {
            super(ID, Health);
            this.heapIndex = heapIndex;
            this.arrayIndex = arrayIndex;
        }

        public int getHeapIndex() {
            return heapIndex;
        }

        protected void setHeapIndex(int j) {
            heapIndex = j;
        }

        public int getArrayIndex() {
            return arrayIndex;
        }

        protected void setArrayIndex(int arrayIndex) {
            this.arrayIndex = arrayIndex;
        }
    } //?----------- end of nested AugmentedHeapPriorityQueueEntry class -----------

    public AugHeapPQ() {
        super();
    }

    public AugHeapPQ(Comparator<Y> comp) {
        super(comp);
    }

    /**
     * Validates an entry to ensure it is location-aware.
     */
    protected AugmentedHeapPQEntry<X, Y> validate(Entry<X, Y> entry) throws IllegalArgumentException {
        if (!(entry instanceof AugHeapPQ.AugmentedHeapPQEntry))
            throw new IllegalArgumentException("Invalid entry");

        AugmentedHeapPQEntry<X, Y> locator = (AugmentedHeapPQEntry<X, Y>) entry; // safe
        int j = locator.getHeapIndex();
        if (j >= heap.size() || heap.get(j) != locator)
            throw new IllegalArgumentException("Invalid entry");

        return locator;
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        super.swap(i, j);   // swap using the base function
        ((AugmentedHeapPQEntry<X, Y>) heap.get(i)).setHeapIndex(i);
        ((AugmentedHeapPQEntry<X, Y>) heap.get(j)).setHeapIndex(j);
    }

    /**
     * Restores the heap property by moving the entry at index j upward/downward.
     */
    protected void bubble(int j) {
        if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0)
            upHeap(j);
        else
            downHeap(j);    // may not be used
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     */
    public Entry<X, Y> add(X ID, Y Health) throws IllegalArgumentException {
        checkHealthComparability(Health);  // might throw an exception if health is not comparable
        Entry<X, Y> newest = new AugmentedHeapPQEntry<>(ID, Health, heap.size(), -1);

        heap.add(heap.size(), newest);   // add to the end of the list
        upHeap(heap.size() - 1);    // upHeap newly added entry

        return newest;
    }

    /**
     * Removes the given entry from the priority queue.
     */
    public void remove(Entry<X, Y> entry) throws IllegalArgumentException {
        AugmentedHeapPQEntry<X, Y> locator = validate(entry);
        int hIndex = locator.getHeapIndex();

        if (hIndex == heap.size() - 1)   // if entry is at last position
            heap.remove(heap.size() - 1);   // just remove it
        else {
            swap(hIndex, heap.size() - 1);   // swap entry to last position
            heap.remove(heap.size() - 1);   // then remove it
            bubble(hIndex);  // and fix heap
        }

    }

    /**
     * Removes the given entry from the priority queue (Overloading method)
     */
    public void remove(int index) throws IllegalArgumentException {
        swap(index, heap.size() - 1);   // swap entry to last position
        heap.remove(heap.size() - 1);   // then remove it
        bubble(index);  // and fix heap
    }

    /**
     * Replaces the value of an entry.
     */
    public void replaceHealth(Entry<X, Y> entry, Y Health) throws IllegalArgumentException {
        AugmentedHeapPQEntry<X, Y> locator = validate(entry);
        checkHealthComparability(Health);
        locator.setHealth(Health);
        bubble(locator.getHeapIndex());
    }

}
