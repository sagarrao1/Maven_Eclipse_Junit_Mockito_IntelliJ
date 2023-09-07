package com.hubberspot.mockito.bdd.stubbing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookService bookService;  
	
	@Test
	void testGetNewBooksWithAppliedDiscount() {
		
		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("222", "Junit5", 400, LocalDate.now());
		List<Book> books =  new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepository.findNewBooks(7)).thenReturn(books);		
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);		
		assertEquals(2, newBooks.size());
		assertEquals(450, newBooks.get(0).getPrice());
		assertEquals(360, newBooks.get(1).getPrice());		
	}

	@Test
	@DisplayName("Given NewBooks, when NewBooksWithApplied method Is Called Then Discount applied to NewBooks is Returned")
	void test_Given_NewBooks_when_GetNewBooksWithAppliedDiscountIsCalled_Then_DiscountAppliedtoNewBooksReturned() {
		// arrange Given
		//action when
		//Assert Then
		 		
		// arrange Given
	 	Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("222", "Junit5", 400, LocalDate.now());
		List<Book> books =  new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		BDDMockito.given(bookRepository.findNewBooks(7)).willReturn(books);
		
		//action when
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);	
		
		//Assert Then
//		assertEquals(2, newBooks.size());
//		assertEquals(450, newBooks.get(0).getPrice());
//		assertEquals(360, newBooks.get(1).getPrice());
		
//		AssertJ -- BDDassertions
		BDDAssertions.then(newBooks.size()).isEqualTo(2);
		BDDAssertions.then(newBooks.get(0).getPrice()).isEqualTo(450);
		BDDAssertions.then(newBooks.get(1).getPrice()).isEqualTo(360);
				
	}
}
