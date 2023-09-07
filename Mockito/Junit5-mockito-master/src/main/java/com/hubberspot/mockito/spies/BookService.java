package com.hubberspot.mockito.spies;

public class BookService {
	
	public Book findBook(String bookId) {
		throw new RuntimeException("Method not implemented");
	}
	
	public int applyDiscountonBook(Book book, int discount) {
		int price= book.getPrice();
		int discountedPrice = price -( price * discount /100);		
		return discountedPrice;
	}
}
