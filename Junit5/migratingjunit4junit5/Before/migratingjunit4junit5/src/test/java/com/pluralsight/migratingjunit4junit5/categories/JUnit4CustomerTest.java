package com.pluralsight.migratingjunit4junit5.categories;

import com.pluralsight.migratingjunit4junit5.tags.Customer;
import org.junit.experimental.categories.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnit4CustomerTest {
    private String CUSTOMER_NAME = "John Smith";

    @Category(IndividualTests.class)
    @Test
    public void testCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);

        assertEquals("John Smith", customer.getName());
    }
}
