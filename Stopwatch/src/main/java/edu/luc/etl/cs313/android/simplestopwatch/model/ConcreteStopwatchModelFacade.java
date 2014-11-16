package edu.luc.etl.cs313.android.simplestopwatch.model;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clicker.ClickCounterModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clicker.DefaultClickCounterModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultStopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */
public class ConcreteStopwatchModelFacade implements StopwatchModelFacade {

	private StopwatchStateMachine stateMachine;

    private ClockModel clockModel;

	private TimeModel timeModel;

    private ClickCounterModel clickCounterModel;

	public ConcreteStopwatchModelFacade() {
		timeModel = new DefaultTimeModel();
		clockModel = new DefaultClockModel();
        clickCounterModel = new DefaultClickCounterModel();
		stateMachine = new DefaultStopwatchStateMachine(timeModel, clockModel, clickCounterModel);
		clockModel.setOnTickListener(stateMachine);
	}

	@Override
	public void onStart() {
		stateMachine.actionInit();
	}

	@Override
	public void setUIUpdateListener(final StopwatchUIUpdateListener listener) {
		stateMachine.setUIUpdateListener(listener);
	}

    @Override
	public void onStartStop() {
		stateMachine.onStartStop();
	}


}
