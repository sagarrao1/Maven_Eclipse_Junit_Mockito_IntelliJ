 package com.pluralsight.migratingjunit4junit5.rules;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomExtension implements AfterEachCallback, BeforeEachCallback {

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " " +
				context.getTestMethod().get().getName() + " has started");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " " +
				context.getTestMethod().get().getName() + " has finished");
	}

}
