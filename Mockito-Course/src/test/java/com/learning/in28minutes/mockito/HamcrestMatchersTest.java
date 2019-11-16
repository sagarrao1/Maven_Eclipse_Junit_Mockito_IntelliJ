package com.learning.in28minutes.mockito;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores = Arrays.asList(12, 34, 11, 45);
		// scores has 4 values
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(34, 12));

		// every Item : > 90
		assertThat(scores, everyItem(greaterThan(9)));
		assertThat(scores, everyItem(lessThan(190)));

		// String related
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());

		// Arrays
		Integer[] marks = { 1, 2, 3, 4 };

		assertThat(marks, arrayWithSize(4));
		assertThat(marks, arrayContaining(1, 2, 3, 4));
		assertThat(marks, arrayContainingInAnyOrder(3, 1, 2, 4));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
