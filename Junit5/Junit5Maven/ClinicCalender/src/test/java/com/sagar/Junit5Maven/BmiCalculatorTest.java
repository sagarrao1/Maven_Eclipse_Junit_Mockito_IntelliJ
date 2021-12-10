package com.sagar.Junit5Maven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BmiCalculator Testing")
class BmiCalculatorTest {

	@Test
	void testCalculateBmi() {
		assertEquals(878.75, BmiCalculator.calculateBmi(45, 6));
		assertEquals(3515.0, BmiCalculator.calculateBmi(45, 3));
	}

}
