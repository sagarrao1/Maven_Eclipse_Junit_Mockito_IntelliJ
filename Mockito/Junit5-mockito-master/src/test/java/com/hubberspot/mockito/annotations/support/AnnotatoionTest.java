package com.hubberspot.mockito.annotations.support;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnnotatoionTest {
	
	@Mock
	BookRepository bookRepositoryMock;

	@InjectMocks
	BookService bookService;
	
	@Test
	void testGetNewBooksWithAppliedDiscountWithAnnotation() {

//		BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
//		BookService bookService = new BookService(bookRepositoryMock);
		
		Book book1 = new Book("333", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("444", "Junit5", 400, LocalDate.now());
		
		List<Book> newBooks = new ArrayList<>();
		newBooks.add(book1);
		newBooks.add(book2);
		
		Mockito.when(bookRepositoryMock.findNewBooks(7)).thenReturn(newBooks);		
		List<Book> newBookStore = bookService.getNewBooksWithAppliedDiscount(10, 7);
		
		assertEquals(2, newBookStore.size());
		assertEquals(450, newBookStore.get(0).getPrice());
		assertEquals(360, newBookStore.get(1).getPrice());
		
	}

}
