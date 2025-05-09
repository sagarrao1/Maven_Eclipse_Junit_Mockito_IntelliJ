package junit.samples;

import junit.framework.*;

/**
 * TestSuite that runs all the sample tests
 *
 */
//comment 1
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All JUnit Tests");
		suite.addTest(VectorTest.suite()); 
		suite.addTest(new TestSuite(junit.samples.money.MoneyTest.class));
		suite.addTest(junit.tests.AllTests.suite());
		return suite;
	}
//	comment 2
}