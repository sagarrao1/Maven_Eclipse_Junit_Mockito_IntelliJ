package com.pluralsight.migratingjunit4junit5.rules;

import com.pluralsight.migratingjunit4junit5.Calculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JUnit4RuleExceptionTester {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot extract the square root of a negative value");
        calculator.sqrt(-1);
    }

    @Test
    public void expectArithmeticException() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("Cannot divide by zero");
        calculator.divide(1, 0);
    }
}
