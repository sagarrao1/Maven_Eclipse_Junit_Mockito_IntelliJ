package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SystemUnderTest.class})
public class MockingConstructorTest {

	// Generally for static method , we use that static Class for prepareforTest
	// here we should not use Constructor class Arraylist.class, instead we use calling method class	
	// which is SystemUnderTest for prepareforTest
	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void testInvokingConstructorMethod() throws Exception {

		ArrayList mockList =  mock(ArrayList.class);		
		when(mockList.size()).thenReturn(10);
				
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
		int result = systemUnderTest.methodUsingAnArrayListConstructor();		
		assertEquals(10, result);
		verify(mockList).size();
	}
}