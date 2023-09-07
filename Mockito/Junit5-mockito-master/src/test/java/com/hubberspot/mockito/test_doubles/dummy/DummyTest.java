package com.hubberspot.mockito.test_doubles.dummy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DummyTest {
	
	Book book1 = new Book("456","Junit5",  100,LocalDate.now() );
	Book book2 = new Book("789","Mokcito", 122,LocalDate.now() );

	@Test
	void demoDummy() {
		BookRepository bookRepositoryFake =  new FakeBookRepository();
		EmailService emailServiceFake =  new DummyEmailService();
				
		BookService bookService = new BookService(bookRepositoryFake, emailServiceFake);
		bookService.addBook(book1);
		bookService.addBook(book2);
		
		assertEquals(2, bookService.findNumberOfBooks());			
	}
	
	@Test
	void demoDummyWithMockito() {
		BookRepository bookRepositoryMock =  mock(BookRepository.class);
		EmailService emailServiceMock =  mock(EmailService.class);
				
		BookService bookService = new BookService(bookRepositoryMock, emailServiceMock);
		
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryMock.findAll()).thenReturn(books);
		
		assertEquals(2, bookService.findNumberOfBooks());	
		
	}

}
