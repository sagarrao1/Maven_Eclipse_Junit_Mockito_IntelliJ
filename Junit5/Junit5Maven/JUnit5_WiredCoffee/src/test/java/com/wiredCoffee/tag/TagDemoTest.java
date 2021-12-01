package com.wiredCoffee.tag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TagDemoTest {

	@DisplayName("TEST MULTIPLE")
	@org.junit.jupiter.api.RepeatedTest(value=3,
			name= "{displayName} - {currentRepetition} / {totalRepetitions}")
	void testMultple(TestInfo info) {
		System.out.println("Test multiple");
//		System.out.println(info.getTestClass().get().getName());
//		System.out.println(info.getTestMethod().get().getName());
		System.out.println(info.getDisplayName());
	}
}
