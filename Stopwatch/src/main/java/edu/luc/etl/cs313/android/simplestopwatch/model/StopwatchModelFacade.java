package edu.luc.etl.cs313.android.simplestopwatch.model;

import edu.luc.etl.cs313.android.simplestopwatch.common.NeedsStopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIListener;


/**
 * A thin model facade. Following the Facade pattern,
 * this isolates the complexity of the model from its clients (usually the adapter).
 *
 * @author laufer
 */
public interface StopwatchModelFacade extends StopwatchUIListener, NeedsStopwatchUIUpdateListener {
    void onStart();
}
