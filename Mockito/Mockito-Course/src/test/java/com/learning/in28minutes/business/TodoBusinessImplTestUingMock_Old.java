package com.learning.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.learning.in28minutes.data.api.TodoService;

//Using Annotations

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplTestUingMock_Old {

	@Mock
	TodoService todoServiceMock;
//	TodoService todoServiceMock = mock(TodoService.class);

	@InjectMocks
	TodoBusinessImpl businessImpl;
//	TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoServiceMock);

	@Test
	public void testRetriveTodoRelatedToSpring_usingMock() {

		List<String> value = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		when(todoServiceMock.retriveTodos("Sagar")).thenReturn(value);

		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingEmptyMock() {

		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingBDDMock() {
//		Given		
		List<String> value = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		given(todoServiceMock.retriveTodos("Sagar")).willReturn(value);

//		When
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Sagar");

//		Then
		assertThat(retriveTodoFiltered.size(), is(2));

	}
}
