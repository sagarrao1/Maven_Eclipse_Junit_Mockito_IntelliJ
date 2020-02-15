package com.learning.in28minutes.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;

public class ListMockTest {

	@Test
	public void testList_size() {
		List listMock= mock(List.class);
		when(listMock.size()).thenReturn(2);
		
		assertEquals(2, listMock.size());
	}

	@Test
	public void testList_get() {
		List listMock= mock(List.class);
		when(listMock.get(0)).thenReturn("Sanju").thenReturn("Ravi");
		
		assertEquals("Sanju", listMock.get(0));
		assertEquals(null, listMock.get(1));
		assertEquals("Ravi", listMock.get(0));
	}
	
	//Matchers
	@Test
	public void testList_getWithAny() {
		List listMock= mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("Sanju");		
// 		If you are using argument matchers, all arguments
//		have to be provided by matchers.
		
		assertEquals("Sanju", listMock.get(0));		
		assertEquals("Sanju1", listMock.get(1));
	}

}
