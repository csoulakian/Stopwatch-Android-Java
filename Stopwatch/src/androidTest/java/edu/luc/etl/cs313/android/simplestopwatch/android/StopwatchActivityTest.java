package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

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

	public void testAActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
	}

    public void testBActivityScenarioInit() throws Throwable {
        actualTest.testActivityScenarioInit();
    }

    @UiThreadTest
    public void testFActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

    @UiThreadTest
    public void testEActivityScenarioRunReset() throws Throwable {
        actualTest.testActivityScenarioRunReset();
	}

    @UiThreadTest
    public void testDActivityScenarioFullRun() throws Throwable {
        actualTest.testActivityScenarioFullRun();
    }

    @UiThreadTest
    public void testHActivityBeepTest() throws Throwable {
        actualTest.testActivityBeepTest();
    }

    @UiThreadTest
    public void testCActivityScenarioInc() throws Throwable {
        actualTest.testActivityScenarioInc();
    }

    public void testGActivityScenarioIncUntilFull() throws Throwable {
        actualTest.testActivityScenarioIncUntilFull();
    }

    public void testIActivityScenarioRotation() throws Throwable {
        actualTest.testActivityScenarioRotation();
    }
}
