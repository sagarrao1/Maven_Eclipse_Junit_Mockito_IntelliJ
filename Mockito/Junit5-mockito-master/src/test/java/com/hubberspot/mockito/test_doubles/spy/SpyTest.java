package com.hubberspot.mockito.test_doubles.spy;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class SpyTest {

	@Test
	void testAddBookifPriceIsGraterThan400() {
		BookRepository bookRepository = spy(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("222", "Junit5", 400, LocalDate.now());
		bookService.addBook(book1);
		bookService.addBook(book2);
		
//		only for book1 save() method is called. not for book2 as price is not more than 400
		
		verify(bookRepository, times(1) ).save(book1);
		verify(bookRepository, never() ).save(book2);
	}

}
