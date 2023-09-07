package com.hubberspot.mockito.argument_captor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
		
	public void addBookWithBookRequest(BookRequest bookRequest) throws SQLException  {	
		Book book1 = new Book();
		book1.setTitle(bookRequest.getTitle());
		book1.setPrice(bookRequest.getPrice());
		book1.setPublishedDate(bookRequest.getPublishedDate());
				
		bookRepository.save(book1);
	}
	
	
}
