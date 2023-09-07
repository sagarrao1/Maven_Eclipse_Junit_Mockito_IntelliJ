package com.hubberspot.mockito.spies;


public class BookManager {
	
	private BookService bookService;

	public BookManager(BookService bookService) {
		this.bookService = bookService;
	}
	
	public int applyDiscountOnBook(String bookId, int discount) {
		Book book = bookService.findBook(bookId); // we need mock
		int discountonBook = bookService.applyDiscountonBook(book, discount); // we need call actual method
		return discountonBook;
	}
	
	
	
}
