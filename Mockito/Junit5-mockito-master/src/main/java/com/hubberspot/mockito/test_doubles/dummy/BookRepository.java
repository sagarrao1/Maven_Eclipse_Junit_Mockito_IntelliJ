package com.hubberspot.mockito.test_doubles.dummy;

import java.util.Collection;

public interface BookRepository {
	public void save(Book book);
	public Collection<Book> findAll();

}
