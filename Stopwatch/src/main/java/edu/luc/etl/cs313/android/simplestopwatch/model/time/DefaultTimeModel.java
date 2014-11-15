package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import android.widget.TextView;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

	private int runningTime = getDisplayedValue;

	private int lapTime = -1;

    @Override
	public void resetRuntime() {
		runningTime = 0;
	}

    @Override
	public void incRuntime() {
		runningTime = (runningTime - 1);
	}

    @Override
	public int getRuntime() {
		return runningTime;
	}

    @Override
	public void setLaptime() {
		lapTime = runningTime;
	}

    @Override
	public int getLaptime() {
		return lapTime;
	}

    protected int getDisplayedValue() {
        final TextView t = (TextView) getActivity().findViewById(R.id.textview_value);
        return Integer.parseInt(t.getText().toString().trim());
    }
}