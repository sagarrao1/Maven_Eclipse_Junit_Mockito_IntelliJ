package com.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MessageServiceTest {
	
	@Test
	public void should_return_hello_worldMsg() {
//		Given
		MessageService messageService = new MessageService();
		String expected="Hello world! from Message service";
				
//		when
		String message = messageService.getMessage();
		
//		then
		assertEquals(expected,message);
//		Assertions.assertThat(message).isEqualTo(expected);
	}

}
