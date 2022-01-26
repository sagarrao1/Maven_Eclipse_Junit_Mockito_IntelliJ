package com.sagar.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloServiceImplTest {
	
	@Test
	public void should_return_hello_worldMsg() {
//		Given
		HelloServiceImpl messageService = new HelloServiceImpl();
		String expected="Hello world!";
				
//		when
		String message = messageService.getMessage();
		
//		then
		assertEquals(expected,message);
//		Assertions.assertThat(message).isEqualTo(expected);
	}

}
