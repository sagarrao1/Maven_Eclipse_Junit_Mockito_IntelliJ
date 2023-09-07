package com.hubberspot.mockito.test_doubles.spy;

public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}	
	
//	 only if book price is grater then 400, then only it save
	public void addBook(Book book) {
		if (book.getPrice() > 400) {
			bookRepository.save(book);
		}
	}
	
	public int findNumberOfBooks() {
		return bookRepository.findAll().size();
	}
	
}
