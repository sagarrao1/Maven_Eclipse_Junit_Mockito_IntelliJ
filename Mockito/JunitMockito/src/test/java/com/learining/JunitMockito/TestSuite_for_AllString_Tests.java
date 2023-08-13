package com.learining.JunitMockito;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StringHelperParameterizedTest.class, 
	StringHelperTest.class,
	StringHelperParameterizedTest2.class })
public class TestSuite_for_AllString_Tests {

}
