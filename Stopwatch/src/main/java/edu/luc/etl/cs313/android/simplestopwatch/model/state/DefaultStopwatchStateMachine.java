package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clicker.ClickCounterModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

	public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel
            clockModel, final ClickCounterModel clickCounterModel) {
		this.timeModel = timeModel;
		this.clockModel = clockModel;
        this.clickCounterModel = clickCounterModel;
	}

	private final TimeModel timeModel;

	private final ClockModel clockModel;

    private final ClickCounterModel clickCounterModel;

	/**
	 * The internal state of this adapter component. Required for the State pattern.
	 */
	private StopwatchState state;

	protected void setState(final StopwatchState state) {
		this.state = state;
		uiUpdateListener.updateState(state.getId());
	}

	private StopwatchUIUpdateListener uiUpdateListener;

	@Override
	public void setUIUpdateListener(final StopwatchUIUpdateListener uiUpdateListener) {
		this.uiUpdateListener = uiUpdateListener;
	}

	// forward event uiUpdateListener methods to the current state
	@Override public void onStartStop() { state.onStartStop(); }
	@Override public void onTick()      { state.onTick(); }

	@Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRuntime()); }

	// known states
	private final StopwatchState STOPPED_RESET  = new StoppedResetState(this);
	private final StopwatchState RUNNING        = new RunningState(this);
	private final StopwatchState INCREMENT      = new IncrementState(this);
	private final StopwatchState STOPPED        = new StoppedState(this);

	// transitions
	@Override public void toRunningState()      { setState(RUNNING); }
	@Override public void toStoppedResetState() { setState(STOPPED_RESET); }
	@Override public void toIncrementState()    { setState(INCREMENT); }
	@Override public void toStoppedState()      { setState(STOPPED); }

	// actions
	@Override public void actionInit()       { toStoppedResetState(); actionReset(); }
	@Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
	@Override public void actionStart()      { clockModel.start(); }
	@Override public void actionStop()       { clockModel.stop(); }
	@Override public void actionDec()        { timeModel.decRuntime(); actionUpdateView(); }
    @Override public void actionInc()        { timeModel.incRuntime(); actionUpdateView(); }
	@Override public void actionUpdateView() { state.updateView(); }
}
