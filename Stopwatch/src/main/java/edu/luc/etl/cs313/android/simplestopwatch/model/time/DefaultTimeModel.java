package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import android.widget.TextView;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

	int runningTime = 0;

    protected final int min = 0;

    protected final int max = 99;


    @Override
	public void resetRuntime() {
		runningTime = 0;
	}

    @Override
	public void decRuntime() {
		if (!isEmpty()){
            runningTime = (runningTime - 1);
        }
	}

    @Override
    public void incRuntime() {
        if (!isFull()){
            runningTime = (runningTime + 1);
        }
    }

    @Override
	public int getRuntime() {
		return runningTime;
	}

    @Override
    public boolean isFull() {
        return runningTime >= max;
    }

    @Override
    public boolean isEmpty() {
        return runningTime <= min;
    }

}