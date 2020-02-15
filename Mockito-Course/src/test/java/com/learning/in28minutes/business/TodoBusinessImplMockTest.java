package com.learning.in28minutes.business;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.learning.in28minutes.data.api.TodoService;


public class TodoBusinessImplMockTest {

	TodoService todoServiceMock;
	TodoBusinessImpl businessImpl;
	
	@Before
	public void setup() {
		todoServiceMock	 = mock(TodoService.class);
		businessImpl = new TodoBusinessImpl(todoServiceMock);
		System.out.println("before");
	}
	
	@After
	public void teardown() {
		todoServiceMock	 = null;
		businessImpl = null;
		System.out.println("after");
	}
	
	@Test
	public void testRetriveTodoRelatedToSpring_usingMock() {
		
		List<String> TodoList = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		
		when(todoServiceMock.retriveTodos("Sagar")).thenReturn(TodoList);
		
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_withEmptyListMock() {
		
		List<String> retriveTodoFiltered = businessImpl.retriveTodoRelatedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	/*
	 * @Test public void testRetriveTodoRelatedToSpring_usingStub3() { List<String>
	 * expectedFilteredArray = businessImpl.retriveTodoRelatedToSpring("Sagar");
	 * List<String> actualArray = Arrays.asList("Learn Spring",
	 * "Learn Spring boot");
	 * 
	 * assertThat(actualArray, is(expectedFilteredArray));
	 * 
	 * }
	 */
}
