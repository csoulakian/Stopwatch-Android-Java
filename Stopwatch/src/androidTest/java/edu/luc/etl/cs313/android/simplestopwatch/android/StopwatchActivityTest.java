package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.test.ActivityInstrumentationTestCase2;

import edu.luc.etl.cs313.android.simplestopwatch.test.android.AbstractStopwatchActivityTest;


/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
public class StopwatchActivityTest extends ActivityInstrumentationTestCase2<StopwatchAdapter> {

	/**
	 * Creates an {@link ActivityInstrumentationTestCase2} for the
	 * {@link SkeletonActivity} activity.
	 */
	public StopwatchActivityTest() {
		super(StopwatchAdapter.class);
        actualTest = new AbstractStopwatchActivityTest() {
            @Override
            protected StopwatchAdapter getActivity() {
                // return activity instance provided by instrumentation test
                return StopwatchActivityTest.this.getActivity();
            }
        };
	}

    private AbstractStopwatchActivityTest actualTest;

	public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
	}

    public void testActivityScenarioInit() throws Throwable {
        actualTest.testActivityScenarioInit();
    }

    public void testActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

    public void testActivityScenarioRunReset() throws Throwable {
        actualTest.testActivityScenarioRunReset();
	}

    public void testActivityScenarioFullRun() throws Throwable {
        actualTest.testActivityScenarioFullRun();
    }

    public void testActivityBeepTest() throws Throwable {
        actualTest.testActivityBeepTest();
    }

    public void testActivityScenarioInc() throws Throwable {
        actualTest.testActivityScenarioInc();
    }

    public void testAActivityScenarioIncUntilFull() throws Throwable {
        actualTest.testActivityScenarioIncUntilFull();
    }

    public void testActivityScenarioRotation() throws Throwable {
        actualTest.testActivityScenarioRotation();
    }
}
