package com.hubberspot.mockito.stubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepositoryMock;
	
	@InjectMocks
	private BookService  bookService;
	
	@Test
	public void testCalculateTotalCost() {
		
		List<String> bookIds = new ArrayList<>();
		bookIds.add("111");
		bookIds.add("222");
		
		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("222", "Junit5", 400, LocalDate.now());
		
		when(bookRepositoryMock.findBookByBookId("111")).thenReturn(book1);		
		when(bookRepositoryMock.findBookByBookId("222")).thenReturn(book2);
		
//		doReturn(book1).when(bookRepositoryMock).findBookByBookId("111");		
//		doReturn(book2).when(bookRepositoryMock).findBookByBookId("222");
		
		int totalCost = bookService.calculateTotalCost(bookIds);
		assertEquals(900, totalCost);
	}

	@Test
	public void testCalculateTotalCostOfBooks() {
		List<String> bookIds = new ArrayList<>();
		bookIds.add("1234");
		bookIds.add("1234");
		
		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());
		
		when(bookRepositoryMock.findBookByBookId("1234"))
				.thenReturn(book1)
				.thenReturn(book1);
//		when(bookRepository.findBookByBookId("1234")).thenReturn(book1);
		
//		doReturn(book1).when(bookRepository).findBookByBookId("1234");
//		doReturn(book2).when(bookRepository).findBookByBookId("1235");
		
		int actualCost = bookService.calculateTotalCost(bookIds);
		assertEquals(1000, actualCost);
	}	
	
	@Test
	public void testSaveBook() {
		Book book1 = new Book(null, "Mockito In Action", 500, LocalDate.now());
		doNothing().when(bookRepositoryMock).save(book1); // ==
		bookService.addBook(book1);
		
		Mockito.verify(bookRepositoryMock).save(book1);		
	}
	
	@Test
	public void testUpdateBookprice() {
		
		Book book = new Book("333", "Mockito In Action", 500, LocalDate.now());		
		Mockito.when(bookRepositoryMock.findBookByBookId("333")).thenReturn(book);
		
		bookService.updateBookprice(600, "333");
		
		Mockito.verify(bookRepositoryMock).save(book);
		
	}
	
	@Test
	public void testUpdateBookpriceIfBookIdisNull() {	
		
		bookService.updateBookprice(600, null);		
		verifyNoInteractions(bookRepositoryMock);
	}
	
	@Test
	public void testUpdateBookpriceIfBookIdPriceisSameItshouldNotSave() {	
		
		Book book = new Book("333", "Mockito In Action", 500, LocalDate.now());		
		Mockito.when(bookRepositoryMock.findBookByBookId("333")).thenReturn(book);
		
		bookService.updateBookprice(500, "333");		
		
//		to verify interactions
		verify(bookRepositoryMock).findBookByBookId("333");
		
		verify(bookRepositoryMock, atLeast(1) ).findBookByBookId("333");
		
//		to verify no more interactions, 3 ways
		verify(bookRepositoryMock, never()).save(book);
		verify(bookRepositoryMock, times(0)).save(book);
		
//		after calling findBookByBookId() , no more interactions to save()
//		if we comment bookService if (book.getPrice() == updatedprice)  if condition, this will fail
		verifyNoMoreInteractions(bookRepositoryMock);
	}
	
	
	@Test
	public void testVerifyingOrderofMethods() {			
		Book book = new Book("333", "Mockito In Action", 500, LocalDate.now());		
		Mockito.when(bookRepositoryMock.findBookByBookId("333")).thenReturn(book);		
		bookService.updateBookprice(600, "333");		
		
		InOrder inOrder = Mockito.inOrder(bookRepositoryMock);
		
//		to verify order of interactions
		inOrder.verify(bookRepositoryMock).findBookByBookId("333");		
		inOrder.verify(bookRepositoryMock).save(book);
		
	}
}
