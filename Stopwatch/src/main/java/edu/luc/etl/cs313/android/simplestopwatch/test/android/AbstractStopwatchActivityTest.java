package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

	/**
	 * Verifies that the activity under test can be launched.
	 */
    @Test
	public void testActivityCheckTestCaseSetUpProperly() {
		assertNotNull("activity should be launched successfully", getActivity());
	}

    // begin-method-testActivityScenarioIncReset
    @Test
    public void testActivityScenarioIncReset() {
        assertTrue(getResetButton().performClick());
        assertEquals(0, getDisplayedValue());
        assertTrue(getIncButton().isEnabled());
        assertFalse(getDecButton().isEnabled());
        assertTrue(getResetButton().isEnabled());
        assertTrue(getIncButton().performClick());
        assertEquals(1, getDisplayedValue());
        assertTrue(getIncButton().isEnabled());
        assertTrue(getDecButton().isEnabled());
        assertTrue(getResetButton().isEnabled());
        assertTrue(getResetButton().performClick());
        assertEquals(0, getDisplayedValue());
        assertTrue(getIncButton().isEnabled());
        assertFalse(getDecButton().isEnabled());
        assertTrue(getResetButton().isEnabled());
        assertTrue(getResetButton().performClick());
    }
    // end-method-testActivityScenarioIncReset

    @Test
    public void testActivityScenarioIncUntilFull() {
        assertTrue(getResetButton().performClick());
        assertEquals(0, getDisplayedValue());
        assertTrue(getIncButton().isEnabled());
        assertFalse(getDecButton().isEnabled());
        assertTrue(getResetButton().isEnabled());
        while (getIncButton().isEnabled()) {
            final int v = getDisplayedValue();
            assertTrue(getIncButton().performClick());
            assertEquals(v + 1, getDisplayedValue());
        }
        assertFalse(getIncButton().isEnabled());
        assertTrue(getDecButton().isEnabled());
        assertTrue(getResetButton().isEnabled());
        assertTrue(getResetButton().performClick());
    }

    // begin-method-testActivityScenarioRotation
    @Test
    public void testActivityScenarioRotation() {
        assertTrue(getResetButton().performClick());
        assertEquals(0, getDisplayedValue());
        assertTrue(getIncButton().performClick());
        assertTrue(getIncButton().performClick());
        assertTrue(getIncButton().performClick());
        assertEquals(3, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        assertEquals(3, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        assertEquals(3, getDisplayedValue());
        assertTrue(getResetButton().performClick());
    }
    // end-method-testActivityScenarioRotation

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
        }});
    }

	/**
	 * Verifies the following scenario: time is 0, press start, wait 5+ seconds, expect time 5.
	 *
	 * @throws Throwable
	 */
    @Test
	public void testActivityScenarioRun() throws Throwable {
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(0, getDisplayedValue());
			assertTrue(getStartStopButton().performClick());
		}});
		Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(5, getDisplayedValue());
			assertTrue(getStartStopButton().performClick());
		}});
	}

	/**
	 * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
	 * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
	 * expect time 5, press lap, expect time 9, press lap, expect time 0.
	 *
	 * @throws Throwable
	 */
    @Test
	public void testActivityScenarioRunLapReset() throws Throwable {
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(0, getDisplayedValue());
			assertTrue(getStartStopButton().performClick());
		}});
		Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(5, getDisplayedValue());
			assertTrue(getResetLapButton().performClick());
		}});
		Thread.sleep(4000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(5, getDisplayedValue());
			assertTrue(getStartStopButton().performClick());
		}});
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(5, getDisplayedValue());
			assertTrue(getResetLapButton().performClick());
		}});
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(9, getDisplayedValue());
			assertTrue(getResetLapButton().performClick());
		}});
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(0, getDisplayedValue());
		}});
	}

	// auxiliary methods for easy access to UI widgets

    protected abstract StopwatchAdapter getActivity();

	protected int tvToInt(final TextView t) {
		return Integer.parseInt(t.getText().toString().trim());
	}

	protected int getDisplayedValue() {
		final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
		final TextView tm = (TextView) getActivity().findViewById(R.id.minutes);
		return SEC_PER_MIN * tvToInt(tm) + tvToInt(ts);
	}

	protected Button getStartStopButton() {
		return (Button) getActivity().findViewById(R.id.startStop);
	}

	protected Button getResetLapButton() {
		return (Button) getActivity().findViewById(R.id.resetLap);
	}

    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
