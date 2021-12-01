package com.wiredCoffee.Extn;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class IllegalArumentExceptionHandlerExtension implements TestExecutionExceptionHandler {

	@Override
	public void handleTestExecutionException(
			ExtensionContext context, Throwable throwable) 
					throws Throwable {
		if (throwable instanceof IllegalArgumentException) {
			System.out.println("Exception of IllegalArgumentException thrown by"
					+ context.getRequiredTestClass().getName()
					+ " # "
					+ context.getRequiredTestMethod().getName()
					);
		return;
		}
		throw throwable;
	}
	

}
