package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class IncrementState implements StopwatchState {

	public IncrementState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

	@Override
	public void onStartStop() {
		onTick();
		sm.toIncrementState();
	}

	@Override
	public void onTick() {
		sm.actionInc();
	}

	@Override
	public void updateView() {
		sm.updateUIRuntime();
	}

	@Override
	public int getId() {
		return R.string.INCREMENT;
	}
}
