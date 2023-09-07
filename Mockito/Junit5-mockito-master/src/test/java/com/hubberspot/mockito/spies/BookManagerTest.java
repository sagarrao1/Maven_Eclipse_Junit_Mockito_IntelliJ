package com.hubberspot.mockito.spies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class BookManagerTest {

	
//	why we are using spy is because we need to use partial MOCK
//	1. we need mock findBook() method
//	2. we need to call applyDiscountOnBook() method on book service which has logic to give discount price
//	3. we need to use doReturn() format when,then won't work for spy 
	@Test
	void testApplyDiscountOnBook() {
		BookService bookServiceSpy =  Mockito.spy(BookService.class);		
		BookManager bookManager = new BookManager(bookServiceSpy);	
		
		Book book1 = new Book("111", "Junit5 Mockito in action", 500, LocalDate.now());		
//		Mockito.when(bookServiceSpy.findBook("111")).thenReturn(book1);		
		Mockito.doReturn(book1).when(bookServiceSpy).findBook(Mockito.anyString());
		
		int discountOnBook = bookManager.applyDiscountOnBook("111", 10);
		assertEquals(450, discountOnBook);
	}

}
