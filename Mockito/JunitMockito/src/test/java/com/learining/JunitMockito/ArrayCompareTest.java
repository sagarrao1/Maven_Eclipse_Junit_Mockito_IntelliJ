package com.learining.JunitMockito;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayCompareTest {

	@Test
	public void testArray() {
		int[] numbers = {12,4,3,1};
		int[] expected = {1,3,4,12};
		Arrays.sort(numbers);
		
		assertArrayEquals(expected, numbers);
	}
	
	@Test(expected = NullPointerException.class)
	public void testArrayWithNull() {
		int[] numbers = null;
		
		Arrays.sort(numbers);
		
		
		//int[] expected = {1,5,4,12};
		
//		try {
//			Arrays.sort(numbers);
//		} catch (Exception e) {
//			//Success TODO: handle exception
//		}
		//assertArrayEquals(null, numbers);
	}
	
	@Test(timeout = 100)
	public void testPerfomance() {		
		int[] numbers= {12,4,56};
		for (int i = 0; i < 1000000; i++) {
			numbers[0]=i;
			Arrays.sort(numbers);
		}
	}
}
	
