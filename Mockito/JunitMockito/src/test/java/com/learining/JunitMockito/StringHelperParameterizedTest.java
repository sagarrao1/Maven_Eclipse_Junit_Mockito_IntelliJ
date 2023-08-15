package com.learining.JunitMockito;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest{
	
	private String input;
	private String expectedOutput;
	
	StringHelper helper= new StringHelper();
	
	@Parameters
	public static Collection<String[]> testConditions(){		
		String[][] expectedArr={{"AACD","CD"},{"ACD","CD"}};
		return Arrays.asList(expectedArr);
	}

	public StringHelperParameterizedTest(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	

	@Test
	public void testReplace2charMethod(){		
		assertEquals(expectedOutput, helper.replaceAinFirst2charactes(input));
		
	}
	
	
	





















/*@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {
	StringHelper helper = new StringHelper();

	String input;
	String expectedOutput;

	public StringHelperParameterizedTest(String input, String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	// ABCD => BCD , BABA => "BBA"

	@Parameters
	public static Collection<String[]> testConditions() {
		String expectedarr[][] = { 
				{ "ABCD", "BCD" }, 
				{ "BABA", "BBA" } };
		return Arrays.asList(expectedarr);
	}

	@Test
	public void testReplaceAinFirst2charactes() {
//		System.out.println("testReplaceAinFirst2charactes");
		assertEquals(expectedOutput, helper.replaceAinFirst2charactes(input));
	}

//	@Test
//	public void testareFirstAndLast2CharsSame() {		
//		System.out.println("testareFirstAndLast2CharsSame");
//		assertTrue(helper.areFirstAndLast2CharsSame("AB"));
//		assertFalse(helper.areFirstAndLast2CharsSame("ABC"));
//		assertTrue(helper.areFirstAndLast2CharsSame("ABAB"));
//		assertFalse (helper.areFirstAndLast2CharsSame("A"));		
//	}

*/

}
