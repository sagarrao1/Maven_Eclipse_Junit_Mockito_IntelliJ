package com.hubberspot.mockito.test_doubles.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class MockTest {

	@Test
	void testAddBookDemoMock() {
		BookRepository bookRepositoryMock =  mock(BookRepository.class);		
		BookService bookService1 = new BookService(bookRepositoryMock);
		
		Book book1 = new Book("333", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("444", "Junit5", 400, LocalDate.now());
		bookService1.addBook(book1);
		bookService1.addBook(book2);
		
		Mockito.verify(bookRepositoryMock, times(1)).save(book1);
		Mockito.verify(bookRepositoryMock, times(0)).save(book2);
		
		Mockito.verify(bookRepositoryMock, never()).save(book2);
	}

}
