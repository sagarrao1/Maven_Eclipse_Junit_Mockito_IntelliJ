package com.learning.in28minutes.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testUsingMockList() {
		List<String> listMock = mock(List.class);
		// Argument Matcher
		when(listMock.get(anyInt())).thenReturn("Sagar");
		assertEquals("Sagar", listMock.get(2));
		assertEquals("Sagar", listMock.get(3));
	}

	@Test
	public void testListSize() {
		List<String> listMock1 = mock(List.class);
		when(listMock1.size()).thenReturn(2);

		assertEquals(2, listMock1.size());
		assertEquals(2, listMock1.size());
		assertEquals(2, listMock1.size());
	}

	@Test
	public void testListSizeMultipleValues() {
		List<String> listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);

		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test(expected = RuntimeException.class)
	public void testListThrowException() {
		List<String> listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Some Exception"));

		assertEquals("Sagar", listMock.get(2));

	}

}
