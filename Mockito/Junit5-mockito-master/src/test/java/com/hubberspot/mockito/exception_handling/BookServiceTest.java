package com.hubberspot.mockito.exception_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
	public void testgetTotalPriceOfBooksThrowsException() throws SQLException {		
		Mockito.when(bookRepositoryMock.findAllBooks()).thenThrow(SQLException.class);		
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());		
	}
	
	@Test
	public void testgetTotalPriceOfBooksThrowsException2() throws SQLException {		
		Mockito.when(bookRepositoryMock.findAllBooks()).thenThrow(new SQLException("database unavailable"));		
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());		
	}
	
//	BDD style
	@Test
	public void testgetTotalPriceOfBooksThrowsExceptionBDDStyle() throws SQLException {		
//		Mockito.when(bookRepositoryMock.findAllBooks()).thenThrow(new SQLException("database unavailable"));		
		BDDMockito.given(bookRepositoryMock.findAllBooks()).willThrow(new SQLException("database unavailable"));
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());		
	}
	
	
	@Test
	public void testAddBookThrowsException() throws SQLException {		
		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());		
		Mockito.doThrow(SQLException.class).when(bookRepositoryMock).save(book1);		
		assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book1));		
	}
	
	
	@Test
	public void testgetTotalPriceOfBooks() throws SQLException {
		
		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		Book book2 = new Book("222", "Junit5", 400, LocalDate.now());
		
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
				
		Mockito.when(bookRepositoryMock.findAllBooks()).thenReturn(books);
		
		int totalPriceOfBooks = bookService.getTotalPriceOfBooks();
		assertEquals(900, totalPriceOfBooks);
	}
	
	
	@Test
	public void testAddBook() throws SQLException {		
		Book book1 = new Book("111", "Junit5", 500, LocalDate.now());
		bookService.addBook(book1);				
		verify(bookRepositoryMock, times(1)).save(book1);	
	}
	
	@Test
	public void TestAddBookWithBookRequest() throws SQLException {		
		BookRequest bookRequest = new BookRequest("Junit5", 500, LocalDate.now());		
		Book book1 = new Book("113", "Junit5", 500, LocalDate.now());
		
		bookService.addBookWithBookRequest(bookRequest);
		verify(bookRepositoryMock, times(1)).save(book1);
	}
}
