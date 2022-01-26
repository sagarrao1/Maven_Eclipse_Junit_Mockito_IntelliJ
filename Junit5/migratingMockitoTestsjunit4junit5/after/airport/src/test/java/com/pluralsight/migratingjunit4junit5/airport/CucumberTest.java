package com.pluralsight.migratingjunit4junit5.airport;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		snippets = SnippetType.CAMELCASE,
		features = "classpath:features" )
public class CucumberTest {
	/**
	 * This class should be empty, step definitions should be in separate classes.
	 */
}
