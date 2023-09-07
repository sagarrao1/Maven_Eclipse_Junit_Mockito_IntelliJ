package com.hubberspot.mockito.argument_captor;

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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
	
	@Captor
	ArgumentCaptor<Book> bookArgumentCaptor;

//	This use BOOK class equals method to compare values
	@Test
	public void TestAddBookWithBookRequest() throws SQLException {		
		BookRequest bookRequest = new BookRequest("Junit5", 500, LocalDate.now());		
		Book book1 = new Book("113", "Junit5", 500, LocalDate.now());
		
		bookService.addBookWithBookRequest(bookRequest);
		verify(bookRepositoryMock, times(1)).save(book1);
	}
	
	
//	Using ArgumentCaptor to capture Book value of save() method on mock oBject (BookRepository)
	@Test
	public void TestSaveBookWithArgumentCaptor() throws SQLException {		
		BookRequest bookRequest = new BookRequest("Junit5 in Action", 500, LocalDate.now());	
		
		ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);		
		
		bookService.addBookWithBookRequest(bookRequest);
		verify(bookRepositoryMock).save(bookArgumentCaptor.capture());
				
		Book book = bookArgumentCaptor.getValue();		
		assertEquals("Junit5 in Action", book.getTitle());
	}

	
//	User ArgumentCaptor annotation same as above
	@Test
	public void TestSaveBookWithArgumentCaptorAnnotation() throws SQLException {		
		BookRequest bookRequest = new BookRequest("Junit5 in Action", 500, LocalDate.now());	
				
		bookService.addBookWithBookRequest(bookRequest);
		verify(bookRepositoryMock).save(bookArgumentCaptor.capture());
				
		Book book = bookArgumentCaptor.getValue();		
		assertEquals("Junit5 in Action", book.getTitle());
	}


}
