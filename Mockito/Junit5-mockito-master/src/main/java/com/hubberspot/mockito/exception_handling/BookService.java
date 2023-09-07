package com.hubberspot.mockito.exception_handling;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public int getTotalPriceOfBooks()  {		
		List<Book> allBooks= null;		
		try {
			allBooks = bookRepository.findAllBooks();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DatabaseReadException("unable to read from database due to "+ e.getMessage());
		}		
		int totalPrie=0;
		for (Book book : allBooks) {
			totalPrie= totalPrie+book.getPrice();
		}		
		return totalPrie;		
	}
	
	
	public void addBook(Book book)  {				
		try {
			bookRepository.save(book);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DatabaseWriteException("Unable to save book due to - "+ e.getMessage());
		}
	}
	
	
	public void addBookWithBookRequest(BookRequest bookRequest) throws SQLException  {			
		Book book1 = new Book("111", bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getPublishedDate());		
		bookRepository.save(book1);
	}
	
	
}
