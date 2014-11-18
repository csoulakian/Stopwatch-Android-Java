package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.app.Activity;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;


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
	 * Verifies the following scenario: time is 0, press button 5 times,
     * wait 4 seconds, expect time 4.
	 *
	 * @throws Throwable
	 */
    @Test
	public void testActivityScenarioRun() throws Throwable {
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(0, getDisplayedValue());
			assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertEquals(5, getDisplayedValue());
		}});
		Thread.sleep(4000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect running state
            assertEquals(4, getDisplayedValue());
		}});
	}

	/**
	 * Verifies the following scenario: time is 0, press button 5 times, expect time 5,
	 * wait 4 seconds, expect time 4, press button, expect time 0.
	 *
	 * @throws Throwable
	 */
    @Test
	public void testActivityScenarioRunReset() throws Throwable {
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertEquals(5, getDisplayedValue());
		}});
		Thread.sleep(4000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect running state
            assertEquals(4, getDisplayedValue());
			assertTrue(getButton().performClick());
		}});
        runUiThreadTasks();
		getActivity().runOnUiThread(new Runnable() { @Override public void run() {
			//expect stopped state
            assertEquals(0, getDisplayedValue());
		}});
	}

    /**
     * Verifies the following scenario: time is 0, press button 5 times,
     * wait 4 seconds, expect time 4, wait 4 seconds, expect time 0, indefinite beeping.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioFullRun() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
        }});
        Thread.sleep(4000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect running state
            assertEquals(4, getDisplayedValue());
        }});
        Thread.sleep(4000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect stopped state
            assertEquals(0, getDisplayedValue());
            //expect indefinite beeping
        }});
    }

    /**
     * Verifies the following scenario: time is 0, press button 5 times,
     * wait 8 seconds, expect time 0, indefinite beeping, press button, no beeping,
     * expect time 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityBeepTest() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
        }});
        Thread.sleep(8000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect stopped state
            assertEquals(0, getDisplayedValue());
            //expect indefinite beeping
            assertTrue(getButton().performClick());
            //expect no beeping
            assertEquals(0, getDisplayedValue());
        }});
    }

    //tests from ClickCounter
    // begin-method-testActivityScenarioInc
    @Test
    public void testActivityScenarioInc() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertEquals(2, getDisplayedValue());
        }});
    }

    // begin-method-testActivityScenarioIncUntilFull
    @Test
    public void testActivityScenarioIncUntilFull() throws Throwable {
            final int max = 10;
            final int v = getDisplayedValue();
            getActivity().runOnUiThread(new Runnable() { @Override public void run() {
                assertEquals(0, getDisplayedValue());
                while (v < max) {
                    assertTrue(getButton().performClick());
                    assertEquals(v + 1, getDisplayedValue());
                }
                //expect beep
            }});
            Thread.sleep(1000); // <-- do not run this in the UI thread!
            //expect running state
            runUiThreadTasks();
            getActivity().runOnUiThread(new Runnable() { @Override public void run() {
                assertEquals(9, getDisplayedValue());
            }});
        }

    // begin-method-testActivityScenarioRotation
    @Test
    public void testActivityScenarioRotation() {
        assertEquals(0, getDisplayedValue());
        assertTrue(getButton().performClick());
        assertTrue(getButton().performClick());
        assertEquals(2, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        assertEquals(2, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        assertEquals(2, getDisplayedValue());
    }


    // auxiliary methods for easy access to UI widgets

    protected abstract StopwatchAdapter getActivity();

	protected int tvToInt(final TextView t) {
		return Integer.parseInt(t.getText().toString().trim());
	}

	protected int getDisplayedValue() {
		final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
		return tvToInt(ts);
	}

	protected Button getButton() {
		return (Button) getActivity().findViewById(R.id.startStop);
	}


    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
