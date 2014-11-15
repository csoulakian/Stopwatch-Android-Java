package edu.luc.etl.cs313.android.simplestopwatch.model.clicker;

/**
 * Created by Chrissy on 11/15/2014.
 */
public interface BoundedCounter {
    void increment();
    void decrement();
    int get();
    boolean isFull();
    boolean isEmpty();
}
