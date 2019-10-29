package com.learning.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.learning.in28minutes.data.api.TodoService;

public class TodoBusinessImplTestUingInjectMockTest {

	@Test
	public void testRetriveTodoRelatedToSpring_usingMock() {

		TodoService todoServiceMock = mock(TodoService.class);
		List<String> value = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		when(todoServiceMock.retriveTodos("Sagar")).thenReturn(value);

		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingEmptyMock() {

		TodoService todoServiceMock = mock(TodoService.class);

		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingBDDMock() {
//		Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> value = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		given(todoServiceMock.retriveTodos("Sagar")).willReturn(value);
		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoServiceMock);

//		When
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Sagar");

//		Then
		assertThat(retriveTodoFiltered.size(), is(2));

	}
}
