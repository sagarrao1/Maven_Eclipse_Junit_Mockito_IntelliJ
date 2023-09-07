package com.hubberspot.mockito.test_doubles.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookRepositoryStub implements BookRepository{

	Book book1 = new Book("111","Junit5",  100,LocalDate.now() );
	Book book2 = new Book("222","Mokcito", 200,LocalDate.now() );
	
	List<Book> bookStore = new ArrayList<>();
		
	@Override
	public List<Book> findNewBooks(int days) {	
		bookStore.add(book1);
		bookStore.add(book2);
		return bookStore;
	}

}
