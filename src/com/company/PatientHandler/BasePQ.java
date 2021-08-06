package com.company.PatientHandler;

import java.util.Comparator;

public abstract class BasePQ<X, Y> implements BasePQInterface<X, Y> {
    // *---------------- nested PriorityQueueEntry class ----------------
    protected static class PriorityQueueEntry<X, Y> implements Entry<X, Y> {
        private X ID;
        private Y health;

        public PriorityQueueEntry(X ID, Y health) {
            this.ID = ID;
            this.health = health;
        }

        @Override
        public X getID() {
            return ID;
        }

        @Override
        public Y getHealth() {
            return health;
        }

        protected void setHealth(Y health) {
            this.health = health;
        }
    } // *----------- end of nested PriorityQueueEntry class -----------

    private Comparator<Y> comp;

    // *Constructors
    protected BasePQ(Comparator<Y> comp) {
        this.comp = comp;
    }
    protected BasePQ(){
        this(new DefaultComp<Y>());
    }

    // Method for comparing two entries according to key
    protected int compare(Entry<X, Y> a, Entry<X, Y> b) {
        return comp.compare(a.getHealth(), b.getHealth());
    }

    protected boolean checkHealthComparability(Y health) throws IllegalArgumentException {
        try {
            return comp.compare(health, health) == 0; // see if health is comparable
        } catch (Exception e) {
            throw new IllegalArgumentException("Health Values Can't be Compared");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
