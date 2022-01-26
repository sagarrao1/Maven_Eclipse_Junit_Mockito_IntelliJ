package io.javabrain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ParameterisedEvenOddTest {
	
	@ParameterizedTest
	@DisplayName("Parameterised Test using csv file source")
	@CsvFileSource(resources = "/evenOdd.csv", numLinesToSkip = 1)
	void evenOrOddTest(String input , String expected) {
		EvenOdd evenOdd= new EvenOdd();		
		String result = evenOdd.evenOrOddNum(Integer.parseInt(input));
		assertEquals(expected, result);
	}

}
