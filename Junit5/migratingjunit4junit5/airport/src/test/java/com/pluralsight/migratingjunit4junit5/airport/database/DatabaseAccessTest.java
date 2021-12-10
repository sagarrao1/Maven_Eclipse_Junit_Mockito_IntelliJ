package com.pluralsight.migratingjunit4junit5.airport.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseAccessTest {
    @Mock
    private Database database;

    private Credentials credentials = new Credentials("user", "password");

    @Test
    public void testUserSuccessfulLogin() {
        when(database.login(credentials)).thenReturn(true);
        Credentials sameCredentials = new Credentials("user", "password");
        assertTrue(database.login(sameCredentials));
    }

    @Test
    public void testUserSuccessfulLogin2() {
        Database database1 = new Database();
        Credentials credentials = new Credentials("user", "password");
        database1.registerUser("user", "password");
        assertTrue(database1.login(credentials));
    }

    @Test
    public void testUserFailedLogin() {
        when(database.login(credentials)).thenReturn(true);
        Credentials otherCredentials = new Credentials("user", "password");
        otherCredentials.setUsername("otherUser");
        otherCredentials.setPassword("otherPassword");
        assertNotEquals(credentials.getUsername(), otherCredentials.getUsername());
        assertNotEquals(credentials.getPassword(), otherCredentials.getPassword());
        assertNotEquals(credentials.hashCode(), otherCredentials.hashCode());
        assertFalse(database.login(otherCredentials));
    }

    @Test
    public void testUserFailedLogin2() {
        Database database1 = new Database();
        Credentials credentials = new Credentials("otheruser", "otherpassword");
        database1.registerUser("user", "password");
        assertFalse(database1.login(credentials));
    }
}
