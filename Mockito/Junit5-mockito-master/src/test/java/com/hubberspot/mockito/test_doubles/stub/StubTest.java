package com.hubberspot.mockito.test_doubles.stub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class StubTest {
	
	@Test
	void testGetNewBooksWithAppliedDiscount() {
		BookRepository bookRepository = new BookRepositoryStub();		
		BookService bookService = new BookService(bookRepository);
		
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);
		
		assertEquals(2, newBooks.size());
		assertEquals(90, newBooks.get(0).getPrice());
		assertEquals(180, newBooks.get(1).getPrice());
		
		
	}

}
