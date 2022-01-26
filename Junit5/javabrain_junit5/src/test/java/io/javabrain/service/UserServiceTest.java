package io.javabrain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import io.javabrain.dao.UserDao;
import io.javabrain.dao.UserDaoImpl;

class UserServiceTest {

	@Test
	void testFindNameById() {
		
		UserDao mockDao= PowerMockito.mock(UserDao.class);		
		PowerMockito.when(mockDao.findNameById(12)).thenReturn("Shekar");	
		UserService service= new  UserService(mockDao);
		String actual = service.findNameById(12);
		assertEquals("Shekar", actual);
	}

	@Test
	void testFindEmailById() {
		UserDaoImpl mockDao1= PowerMockito.mock(UserDaoImpl.class);		
//		PowerMockito.when(mockDao1.findEmailById(12)).thenReturn("Shekar@gmail.com");	
		PowerMockito.when(mockDao1.findEmailById(12)).thenCallRealMethod();
		UserService service= new  UserService(mockDao1);
		String actual = service.findEmailById(12);
//		assertEquals("Shekar@gmail.com", actual);
		assertEquals("Sagarrao1@gmail.com", actual);	
	}
	
	@Test
	void testdoWorkPrivateMethod() throws Exception {
		UserService service = new UserService();		
//		UserService spy = PowerMockito.spy(service);
//		
//		PowerMockito.doReturn("MAHESH KV").when(spy, "formatMsg","mahesh kv");
		
		String actual = service.doWork("mahesh kv");
		assertEquals("MAHESH KV", actual);
		
		
	}

}
