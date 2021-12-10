package com.pluralsight.migratingjunit4junit5.tag;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.pluralsight.migratingjunit4junit5.tags.Customer;

@Tag("individual")
public class JUnit5CustomerTest {
    private String CUSTOMER_NAME = "John Smith";

    @Test
    public void testCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);

        assertEquals("John Smith", customer.getName());
    }
}
