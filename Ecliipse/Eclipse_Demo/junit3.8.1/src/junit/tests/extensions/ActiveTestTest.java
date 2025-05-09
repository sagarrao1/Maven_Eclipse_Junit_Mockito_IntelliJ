package junit.tests.extensions;

import junit.framework.*;
import junit.extensions.*;

/**
 * Testing the ActiveTest support
 */

public class ActiveTestTest extends TestCase {

	public static class SuccessTest extends TestCase {
		public void runTest() {
		}
	}

	public void testActiveTest() {
		Test test = createActiveTestSuite();
		TestResult result = new TestResult();
		test.run(result);
		assertEquals(100, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	public void testActiveRepeatedTest() {
		Test test = new RepeatedTest(createActiveTestSuite(), 5);
		TestResult result = new TestResult();
		test.run(result);
		assertEquals(500, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	public void testActiveRepeatedTest0() {
		Test test = new RepeatedTest(createActiveTestSuite(), 0);
		TestResult result = new TestResult();
		test.run(result);
		assertEquals(0, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	public void testActiveRepeatedTest1() {
		Test test = new RepeatedTest(createActiveTestSuite(), 1);
		TestResult result = new TestResult();
		test.run(result);
		assertEquals(100, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	ActiveTestSuite createActiveTestSuite() {
		ActiveTestSuite suite = new ActiveTestSuite();
		for (int i = 0; i < 100; i++)
			suite.addTest(new SuccessTest());
		return suite;
	}

}