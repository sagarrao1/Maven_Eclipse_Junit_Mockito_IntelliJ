package com.learning.in28minutes.mockito;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores = Arrays.asList(2, 34, 11, 5);
		// scores has 4 values
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(34, 11));

		// every Item : > 90
		assertThat(scores, hasItem(greaterThan(32)));
	}

}
