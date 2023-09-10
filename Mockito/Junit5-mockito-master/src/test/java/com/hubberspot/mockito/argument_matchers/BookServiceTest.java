package com.hubberspot.mockito.argument_matchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@Mock
	private BookRepository bookRepositoryMock;
	
	@InjectMocks
	private BookService bookService;
	
	@Test
	void testUpdatePriceArgumentMacther() {
		Book book = new Book("113", "Junit5", 500, LocalDate.now());
		
		Mockito.when(bookRepositoryMock.findBookById( Mockito.anyString() ))
					.thenReturn(book);				
		bookService.updatePrice("124", 600);
		verify(bookRepositoryMock, times(1)).save(book);
		assertEquals(600, book.getPrice());
	}
	
//	Same above with Argument Captor
	@Test
	void testUpdatePriceWithArgumentCaptor() {
		Book book = new Book("113", "Junit5", 500, LocalDate.now());
		
		Mockito.when(bookRepositoryMock.findBookById( Mockito.anyString() ))
					.thenReturn(book);
		
		ArgumentCaptor<Book> bookArgCaptor= ArgumentCaptor.forClass(Book.class);
		
		bookService.updatePrice("124", 600);
		verify(bookRepositoryMock, times(1)).save(bookArgCaptor.capture());
		Book book2 = bookArgCaptor.getValue();
		assertEquals(600, book2.getPrice());
	}

	//example of any(), eq(), anyString() type argument matchers 
	@Test
	void testInvalidUseOfArgumentMatcher() {
		
		Book book = new Book("113", "Junit5 in action", 500, LocalDate.now());
		
//		Argument matchers should be used for all , not for one
//		Mockito.when(bookRepositoryMock.findBookByTitleAndPublishedDate("Junit5 in action", Mockito.any()))
//						.thenReturn(book);
		
		Mockito.when(bookRepositoryMock.findBookByTitleAndPublishedDate(Mockito.eq("Junit5 in action"), Mockito.any()))
		.thenReturn(book);		
		Book actualBook = bookService.getBookByTitleAndPublishedDate("Junit5 in action", LocalDate.now());
		assertEquals("Junit5 in action", actualBook.getTitle());		
	}
	
	//example of anyBoolean(), anyInt(), anyString() type argument matchers 
	@Test
	void testSpecificTypeArgumentMatchers() {
		
		Book book = new Book("113", "Junit5 in action", 500, LocalDate.now(), true);		
//		Argument matchers should be used for all , not for one
//		Mockito.when(bookRepositoryMock.findBookByTitleAndPublishedDate("Junit5 in action", Mockito.any()))
//						.thenReturn(book);		
		Mockito.when(bookRepositoryMock.findBookByTitleAndPriceAndIsDigital(Mockito.anyString(), Mockito.anyInt(), Mockito.anyBoolean() ))
		.thenReturn(book);
		
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Junit5 in action", 500, true);
		assertEquals("Junit5 in action", actualBook.getTitle());		
	}
	
	//example of anyCollection(), anyInt(), anyString() type argument matchers
//	Argument macthes can be used only in stubbing, when() or verification 
	@Test
	void testCollectionTypeArgumentMatchers() {
		
		Book book1 = new Book("111", "Junit5 in action", 500, LocalDate.now());
		Book book2 = new Book("222", "Python", 600, LocalDate.now());
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
//		Mockito.when(bookRepositoryMock.findBookByTitleAndPriceAndIsDigital(Mockito.anyString(), Mockito.anyInt(), Mockito.anyBoolean() ))
//		.thenReturn(book);		
		bookService.addBooks(books);
//		Instead of this , we can use anyList(), anyMap() like that depending of collection used in BookService class
//		verify(bookRepositoryMock).saveAll(books);
		verify(bookRepositoryMock).saveAll(Mockito.anyList());		
	}
	
	@Test
	void testStringTypeArgumentMatchers() {
		
		Book book = new Book("113", "Junit5 in action", 500, LocalDate.now(), true);		
//		Mockito.when(bookRepositoryMock.findBookByTitleAndPriceAndIsDigital(Mockito.startsWith("Junit5"), Mockito.anyInt(), Mockito.anyBoolean() ))
//		.thenReturn(book);
		
		Mockito.when(bookRepositoryMock.findBookByTitleAndPriceAndIsDigital(Mockito.contains("action"), Mockito.anyInt(), Mockito.anyBoolean() ))
		.thenReturn(book);
		
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Junit5 in action", 500, true);
		assertEquals("Junit5 in action", actualBook.getTitle());		
	}
}
