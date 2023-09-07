package com.hubberspot.mockito.test_doubles.fake;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FakeTest {
	
	Book book1 = new Book("1234","Junit5",  100,LocalDate.now() );
	Book book2 = new Book("1235","Mokcito", 122,LocalDate.now() );

	@Test
	void testFake() {
		BookRepository fakeBookRepository = new FakeBookRepository();
		BookService bookService = new BookService(fakeBookRepository);
		
		bookService.addBook(book1 );
		bookService.addBook(book2);
		
		assertEquals(2, bookService.findNumberOfBooks());
	}

	@Test
	void testFakeWithMockito() {
		BookRepository bookRepositoryMock = mock(BookRepository.class);
		BookService bookService = new BookService(bookRepositoryMock);
		
		List<Book> books= new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryMock.findAll()).thenReturn(books);
		
		assertEquals(2, bookService.findNumberOfBooks());
		
	}
	
	
	@Test
	void testFakeWithMockitoAddBook() {
		BookRepository bookRepositoryMock = mock(BookRepository.class);
		BookService bookService = new BookService(bookRepositoryMock);
		
		bookService.addBook(book1);		
		verify(bookRepositoryMock, atLeastOnce()).save(book1);
		
	}
	
	
	
	
}
