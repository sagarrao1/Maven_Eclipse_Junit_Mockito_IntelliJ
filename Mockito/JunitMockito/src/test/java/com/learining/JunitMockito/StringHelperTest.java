/**
 * 
 */
package com.learining.JunitMockito;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sagar
 *
 */
class SuperTestClass {

}

public class StringHelperTest extends SuperTestClass {
	StringHelper helper;

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		System.out.println(" Before class");
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		System.out.println(" After class");
//	}

	@Before
	public void setUp() throws Exception {
//		System.out.println(" Before");
		helper = new StringHelper();
	}

	@After
	public void tearDown() throws Exception {
//		System.out.println(" After...");
		helper = null;
	}

	@Test
	public void testReplaceAinFirst2charactes() {

//		System.out.println("testReplaceAinFirst2charactes");
		assertEquals("BCD", helper.replaceAinFirst2charactes("ABCD"));
		assertEquals("", helper.replaceAinFirst2charactes("AA"));
		assertEquals("BBA", helper.replaceAinFirst2charactes("BABA"));
	}

	@Test
	public void testareFirstAndLast2CharsSame() {

//		System.out.println("testareFirstAndLast2CharsSame");
		assertTrue(helper.areFirstAndLast2CharsSame("AB"));
		assertFalse(helper.areFirstAndLast2CharsSame("ABC"));
		assertTrue(helper.areFirstAndLast2CharsSame("ABAB"));
		assertFalse(helper.areFirstAndLast2CharsSame("ABA"));
		assertTrue(helper.areFirstAndLast2CharsSame("AAA"));
		assertFalse(helper.areFirstAndLast2CharsSame("A"));

	}

	@Test
	public void testAssertFalse() {
		assertFalse("Condition failed", false);
	}

	/* Arrays.sort */
	@Test
	public void testArraysSort() {
		int[] numbers = { 2, 4, 5, 6, 1 };
		Arrays.sort(numbers);
		int[] expectedArr = { 1, 2, 4, 5, 6 };

		for (int i = 0; i < numbers.length; i++) {
//			System.out.println(numbers[i]);
		}
		assertArrayEquals(expectedArr, numbers);
//		int array[]= {12,13,14};
//		array[0]=1;
//		
//		for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]);	
//		}

	}

	@Test(expected = NullPointerException.class)
	public void testArraySortWithNullCondition() {
		int[] numbers = null;
		Arrays.sort(numbers);
	}

	@Test(timeout = 10)
	public void testArrayPerfomance() {
		for (int i = 0; i < 100000; i++) {
			int[] nums = { i, i - 1, i + 2 };
			Arrays.sort(nums);
		}
	}

}
