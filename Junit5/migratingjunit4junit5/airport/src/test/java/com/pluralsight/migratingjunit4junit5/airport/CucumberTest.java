package com.pluralsight.migratingjunit4junit5.airport;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Entry point for running the Cucumber tests in JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = {"pretty"},
	snippets = SnippetType.CAMELCASE,
	features = "classpath:features")
public class CucumberTest {

	/**
	 * This class should be empty, step definitions should be in separate classes.
	 */

}
