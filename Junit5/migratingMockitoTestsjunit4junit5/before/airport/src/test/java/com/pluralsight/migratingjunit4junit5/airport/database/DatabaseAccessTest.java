package com.pluralsight.migratingjunit4junit5.airport.database;

import org.junit.After;
import org.junit.Before;
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
	
	private Credentials credentials= new Credentials("user", "password");
	
	private Database database2;
	private Credentials credentials2;
	
	@Before
	public void setup() {		
		credentials2= new Credentials("user", "password");
		database2= new Database();
	}
	
	@Test
	public void testUserSuccessfulLogin() {		
		when(database.login(credentials)).thenReturn(true);
		boolean login = database.login(credentials2);
		credentials2.setUsername("user");
		credentials2.setPassword("password");
		assertTrue(login);
	}
	
	@Test
	public void testUserSuccessfulLogin2() {
		database2.registerUser("user", "password");		
		boolean login2 = database2.login(credentials2);
		assertTrue(login2);
	}

	@Test
	public void testUserFailedLogin() {		
		when(database.login(credentials)).thenReturn(true);
		Credentials otherCredentials= new Credentials("user", "password");
		otherCredentials.setUsername("OtherUser");
		otherCredentials.setPassword("OtherPassword");
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
	
	
	@After
	public void teardown(){
		credentials2=null;
		database2=null;
		System.out.println(" After block in teardown");
	}

}














