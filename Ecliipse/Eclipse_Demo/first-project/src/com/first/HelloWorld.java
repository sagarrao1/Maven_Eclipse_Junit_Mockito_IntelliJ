package com.first;

import com.service.MessageService;

/*
 * 
 */
public class HelloWorld {

//	private boolean sometest;

	public static void main(String[] args) {
		
		MessageService messageService=  new MessageService();
		System.out.println(messageService.getMessage());
	}
	

}
