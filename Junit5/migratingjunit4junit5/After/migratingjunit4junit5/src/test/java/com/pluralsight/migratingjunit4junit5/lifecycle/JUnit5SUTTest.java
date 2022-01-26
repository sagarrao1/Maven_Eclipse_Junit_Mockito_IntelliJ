package com.pluralsight.migratingjunit4junit5.lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class JUnit5SUTTest {

    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeAll
    public static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("Our resource for all tests");
    }

    @AfterAll
    public static void tearDownClass() {
        resourceForAllTests.close();
    }

    @BeforeEach
    public void setUp() {
        systemUnderTest = new SUT("Our system under test");
    }

    @AfterEach
    public void tearDown() {
        systemUnderTest.close();
    }

    @Test
    public void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();

        assertTrue(canReceiveRegularWork);
    }

    @Test
    public void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();

        assertFalse(canReceiveAdditionalWork);
    }

    @Test
    @Disabled
    public void myThirdTest() {
        assertEquals( 2, 1,"2 is not equal to 1");
    }
}
