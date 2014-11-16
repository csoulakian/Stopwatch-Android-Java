package edu.luc.etl.cs313.android.simplestopwatch.model.clicker;

/**
 * Created by Chrissy on 11/15/2014.
 */
public class DefaultClickCounterModel implements ClickCounterModel {
    /** The lower bound of the counter. */
    private final int min;

    /** The upper bound of the counter. */
    private final int max;

    /** The current value of the counter. */
    private int value;

    /** Constructs a bounded counter with the default bounds. */
    public DefaultClickCounterModel() {
        this(0, 99);
    }

    /**
     * Constructs a bounded counter with the given bounds.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @throws IllegalArgumentException if min > max
     */
    public DefaultClickCounterModel(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min >= max");
        }
        this.min = min;
        this.max = max;
        this.value = this.min;
    }

    /**
     * Indicates whether this counter currently satisfies its internal data
     * invariant: the counter value is within the bounds.
     *
     * @return whether the data invariant is satisfied
     */
    protected boolean dataInvariant() {
        return min <= value && value <= max;
    }

    @Override
    public void increment() {
        assert dataInvariant() && !isFull();
        ++value;
        assert dataInvariant();
    }

    @Override
    public void decrement() {
        assert dataInvariant() && !isEmpty();
        --value;
        assert dataInvariant();
    }

    @Override
    public void reset() {
        value = min;
    }


    @Override
    public int get() {
        return value;
    }

    @Override
    public boolean isFull() {
        return value >= max;
    }

    @Override
    public boolean isEmpty() {
        return value <= min;
    }
}
