package edu.luc.etl.cs313.android.simplestopwatch.common;

/**
 * A listener for stopwatch events coming from the UI.
 *
 * @author laufer
 */
public interface StopwatchUIListener {
	public void onStartStop();
	public void onLapReset();
}
