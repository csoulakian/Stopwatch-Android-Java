package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel {
	void start();
	void stop();
	void setOnTickListener(OnTickListener listener);
}
