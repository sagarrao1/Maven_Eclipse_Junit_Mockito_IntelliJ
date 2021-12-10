package com.pluralsight.migratingjunit4junit5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.pluralsight.migratingjunit4junit5.Calculator;

public class JUnit5RuleExceptionTester {
    
    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
    	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.sqrt(-1));        
    	assertEquals("Cannot extract the square root of a negative value",exception.getMessage());
    }

    @Test
    public void expectArithmeticException() {
    	
    	Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    	assertEquals("Cannot divide by zero",exception.getMessage());    	
    	
    }
}
