package com.learning.in28minutes.business;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.learning.in28minutes.data.api.TodoService;

// With Runners , you can only use one runner at at time, you can't use other runner like springrunner
// other option is to use rule istead of runners.

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplTestUsingInjectMocksTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl businessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
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

	@Test
	public void testRetriveTodoRelatedToSpring_usingBDDMock() {
		
		List<String> TodoList = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");
		
		//given		
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

	//Capture arguments that you pass to method
	//using ArgumentCaptor class
	@Test
	public void testdeleteTodoNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Declare argument Capture
		//ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
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
