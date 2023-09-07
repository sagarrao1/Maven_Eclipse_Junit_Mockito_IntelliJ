package com.hubberspot.mockito.exception_handling;

public class DatabaseWriteException extends RuntimeException {
	
	DatabaseWriteException(String message){
		super(message);
	}

}
