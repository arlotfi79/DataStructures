package com.company.PatientHandler;


public class ProbeHashMap<X, Y> extends AbstractHashMap<X, Y> {
    private Entry<X, Y>[] table; // static generic Array filled with Null
    private final Entry<X, Y> DELETED = new AugHeapPQ.AugmentedHeapPQEntry<>(null, null, -1, -1);

    // * Constructors
    public ProbeHashMap() {
        super();
    }
    public ProbeHashMap(int capacity) {
        super(capacity);
    }
    public ProbeHashMap(int capacity, int p) {
        super(capacity, p);
    }

    /**
     * Creates an empty table with wanted Capacity
     */
    protected void createTable() {
        table = (Entry<X, Y>[]) new Entry[capacity]; // safe cast
    }

    /** @returns true if location is either empty or the "Deleted" Entry */
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DELETED);
    }

    /** @returns index with X ID, or −(a+1) such that ID could be added at index a*/
    private int findSlot(int h, X ID) {
        int slotIndex = -1; // NO slot available
        int i = h;  // index for the loop

        do {
            if (isAvailable(i)) {
                if (slotIndex == -1)    // for the first iteration
                    slotIndex = i;
                if (table[i] == null)   // if empty, search fails
                    break;
            } else if (table[i].getID().equals(ID))
                return i;   // successful

            i = (i + 1) % capacity;   // keep looking
        } while (i != h);   // stop if we return to the start index

        return -(slotIndex + 1);    // search has failed
    }

    protected Entry<X, Y> getEntry(int h, X ID) {
        int j = findSlot(h, ID);
        if (j < 0)  // no match found
            return null;

        return table[j];
    }

    protected Entry<X, Y> addEntry(int h, Entry<X, Y> entry) throws IllegalArgumentException{
        int j = findSlot(h, entry.getID());
        if (j >= 0) { // this key has an existing entry
            throw new IllegalArgumentException("Entry With the Same ID is Available");
        }

        table[-(j + 1)] = entry;
        size++;

        return entry;
    }

    protected Entry<X, Y> removeEntry(int h, X ID) {
        int j = findSlot(h, ID);
        if (j < 0)  // nothing to remove
            return null;

        Entry<X, Y> answer = table[j];
        table[j] = DELETED; // mark this slot as deactivated
        size--;

        return answer;
    }

}
