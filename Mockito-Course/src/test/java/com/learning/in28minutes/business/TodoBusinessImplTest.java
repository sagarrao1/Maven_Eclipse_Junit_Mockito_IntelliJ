package com.learning.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.learning.in28minutes.data.api.TodoService;
import com.learning.in28minutes.data.api.TodoServiceStub;

public class TodoBusinessImplTest {

	@Test
	public void testRetriveTodoRelatedToSpring_usingStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoService);
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingStub2() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoService);
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingStub3() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoService);
		List<String> expectedFilteredArray = businessImpl.retriveTodoRelatedToSpring("Sagar");
		List<String> actualArray = Arrays.asList("Learn Spring", "Learn Spring boot");

		assertThat(actualArray, is(expectedFilteredArray));

	}

}
