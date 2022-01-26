package com.pluralsight.migratingjunit4junit5.airport.database;


import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DatabaseAccessTest {

	@Mock
	private Database database;	
	private Credentials credentials= new Credentials("user", "password");
	
	private Database database2;
	private Credentials credentials2;
	
	@BeforeEach
	public void setup() {		
		credentials2= new Credentials("user", "password");
		database2= new Database();
	}
	
	@Test
	public void testUserSuccessfulLogin() {		
		when(database.login(credentials)).thenReturn(true);
		boolean login = database.login(credentials2);
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
//		when(database.login(credentials)).thenReturn(true);
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
	
	
	@AfterEach
	public void teardown(){
		credentials2=null;
		database2=null;
		System.out.println(" After block in teardown");
	}

}














