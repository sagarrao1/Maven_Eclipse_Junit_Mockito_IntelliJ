package com.learining.JunitMockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest2 {
	StringHelper helper = new StringHelper();

	private String input;
	private String expectedOutput;
	
	public StringHelperParameterizedTest2(String input,String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput=expectedOutput;
	}
	// AB => true , ABAB => true , A => false , ABCD => false	
	
	@Parameters
	public static List<String[]> testConditions() {
		String strArr[][] = { {"AB","true"},  {"ABAB","true"} ,{"A","false"},  {"ABCD","false"}  };
		return Arrays.asList(strArr);	
	}

//	@Test
//	public void testReplaceAinFirst2charactes() {
//		System.out.println("testReplaceAinFirst2charactes");
//		assertEquals(expectedOutput, helper.replaceAinFirst2charactes(input));
//	}

	@Test
	public void testareFirstAndLast2CharsSame() {
	
//		System.out.println("testareFirstAndLast2CharsSame");
		assertEquals(Boolean.parseBoolean(expectedOutput), helper.areFirstAndLast2CharsSame(input));		
	}

}
