package edu.luc.etl.cs313.android.simplestopwatch.model.clicker;

/**
 * Created by Chrissy on 11/15/2014.
 */

import edu.luc.etl.cs313.android.simplestopwatch.model.clicker.BoundedCounter;

/**
 * Adapter for using the CLI bounded counter as the Android click counter model.
 */
public class BoundedCounterWrapper implements ClickCounterModel {

    final BoundedCounter counter;

    public BoundedCounterWrapper(final BoundedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void increment() {
        counter.increment();
    }

    @Override
    public void decrement() {
        counter.decrement();
    }

    @Override
    public void reset() {
        if (!counter.isEmpty()) {
            counter.decrement();
        }
    }

    @Override
    public int get() {
        return counter.get();
    }

    @Override
    public boolean isFull() {
        return counter.isFull();
    }

    @Override
    public boolean isEmpty() {
        return counter.isEmpty();
    }
}

