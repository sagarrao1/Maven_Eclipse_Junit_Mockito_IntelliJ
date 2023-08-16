package com.learning.in28minutes.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		List arrayListMock = mock(ArrayList.class);
		assertEquals(0, arrayListMock.size());

		when(arrayListMock.size()).thenReturn(5);
		arrayListMock.add("Dummy");

		assertEquals(5, arrayListMock.size());

	}

	// Spy is like creating new arrayList<>();
	// mock is creating stub. dummy ArrayList
	// Spy is also called Partial Mock
	@Test
	public void testSpy() {
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");

		assertEquals(1, arrayListSpy.size());
		System.out.println(arrayListSpy.get(0));

		when(arrayListSpy.size()).thenReturn(5);

//		To verify particular method is called we use spy
		verify(arrayListSpy).add("Dummy");

		verify(arrayListSpy, never()).clear();
		verify(arrayListSpy, never()).remove(0);

		assertEquals(5, arrayListSpy.size());

	}
}
