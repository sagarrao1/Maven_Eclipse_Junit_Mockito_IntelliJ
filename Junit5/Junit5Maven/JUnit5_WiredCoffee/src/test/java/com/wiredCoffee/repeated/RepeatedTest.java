package com.wiredCoffee.repeated;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;


public class RepeatedTest {

	
	@Test
	@Tag("DEV")
	void testDevA(TestInfo info) {
		System.out.println("test Dev A");
	}
	
	@Test
	@Tag("DEV")
	void testDevB() {
		System.out.println("test Dev B");
	}
	
	@Test
	@Tag("QA")
	void testQaA() {
		System.out.println("test QA B");
	}
	
	@Test
	@Tag("QA")
	void testQAB() {
		System.out.println("test QA B");
	}
	
	
	
}
