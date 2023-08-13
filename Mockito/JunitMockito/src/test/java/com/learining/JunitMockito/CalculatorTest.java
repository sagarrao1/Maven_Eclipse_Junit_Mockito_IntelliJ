package com.learining.JunitMockito;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

public class CalculatorTest  {
	
	Calculator c = null;

	@Mock
	CalculatorService service;
	
	@Rule 
	public MockitoRule mockitoRule= MockitoJUnit.rule();
	
	//CalculatorService service = mock(CalculatorService.class); 
			
			/*new CalculatorService() {
		
		public int add(int i, int j) {
			// TODO Auto-generated method stub
			return 0;
		}
	};*/
	
	@Before	
	public void setup() {		
		
		c= new Calculator(service);
	}
	
	
	@Test
	public void testPerform() {
		
		when(service.add(2,3)).thenReturn(5);		
		assertEquals(10, c.perform(2, 3));
		verify(service, atLeast(1)).add(anyInt(), eq(3));
		
	}

}
