package com.learning.in28minutes.mockito;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		
		List<Integer> scores = Arrays.asList(90,100,105,80);
		
		//verify scores
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(90,100));
		assertThat(scores, everyItem(greaterThanOrEqualTo(80)));
		assertThat(scores, everyItem(lessThan(160)));
		
		//String
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
		
		//Array
		Integer[] values= {1,2,3,4};
		
		assertThat(values, arrayWithSize(4));
		assertThat(values, arrayContaining(1,2,3,4));
		assertThat(values, arrayContainingInAnyOrder(2,3,1,4));

		assertThat(values, hasItemInArray(3));
		
	}

}
