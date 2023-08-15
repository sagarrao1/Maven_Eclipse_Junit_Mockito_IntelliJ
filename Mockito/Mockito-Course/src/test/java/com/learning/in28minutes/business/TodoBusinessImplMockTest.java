package com.learning.in28minutes.business;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
		
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_withEmptyListMock() {
		
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	
//	BDD style of writting test cases
//	Given , when , then 
	@Test
	public void testRetriveTodoRelatedToSpring_usingBDDMock() {
		//given		
		List<String> TodoList = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		given(todoServiceMock.retriveTodos("Sagar")).willReturn(TodoList);
		
		//when
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Sagar");
		
		//then
		assertThat(retriveTodoFiltered.size(),is(2));

	}

	// to verify specific method is called or not 
	// here to verify deleteTodos() is called or not 
	@Test
	public void testdeleteTodoNotRelatedToSpring_usingBDDMock() {
		
		List<String> TodoList = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		
		//given		
		given(todoServiceMock.retriveTodos("Sagar")).willReturn(TodoList);
		
		//when
		businessImpl.deleteTodoNotRelatedToSpring("Sagar");
		
		//then
		verify(todoServiceMock).deleteTodo("Learn Java");
		
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring boot");
		
		verify(todoServiceMock,times(1)).deleteTodo("Learn Java");
		verify(todoServiceMock,atLeastOnce()).deleteTodo("Learn Java");
		verify(todoServiceMock,atLeast(1)).deleteTodo("Learn Java");
	}

	//Capute arguments that you pass to method
	//using ArgumentCaptor class
	@Test
	public void testdeleteTodoNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Declare argument Capture
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Define it on method
		//Capture or assert or verify that argument 
		
		
		List<String> TodoList = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		
		//given		
		given(todoServiceMock.retriveTodos("Sagar")).willReturn(TodoList);
		
		//when
		businessImpl.deleteTodoNotRelatedToSpring("Sagar");
		
		//then
		verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());		
		assertThat(stringArgumentCaptor.getValue(), is("Learn Java"));
		
	}

}
