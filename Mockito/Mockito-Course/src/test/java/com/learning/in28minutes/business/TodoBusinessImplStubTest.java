package com.learning.in28minutes.business;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.learning.in28minutes.data.api.TodoService;
import com.learning.in28minutes.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	TodoService todoServiceStub;
	TodoBusinessImpl businessImpl;
	
	@Before
	public void setup() {
		todoServiceStub	 = new TodoServiceStub();
		businessImpl = new TodoBusinessImpl(todoServiceStub);
		System.out.println("before");
	}
	
	@After
	public void teardown() {
		todoServiceStub	 = null;
		businessImpl = null;
		System.out.println("after");
	}
	
	@Test
	public void testRetriveTodoRelatedToSpring_usingStub() {
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Sagar");
		assertEquals(2, retriveTodoFiltered.size());

	}

	@Test
	public void testRetriveTodoRelatedToSpring_usingStub2() {
		List<String> retriveTodoFiltered = businessImpl.retriveTodosRealtedToSpring("Dummy");
		assertEquals(0, retriveTodoFiltered.size());

	}

	
	  @Test public void testRetriveTodoRelatedToSpring_usingStub3() { List<String>
	  expectedFilteredArray = businessImpl.retriveTodosRealtedToSpring("Sagar");
	  List<String> actualArray = Arrays.asList("Learn Spring",
	  "Learn Spring boot");
	  
	  assertThat(actualArray, is(expectedFilteredArray));
	  
	  }
	 
}
