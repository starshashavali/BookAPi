package com.org.service;

import java.util.List;

import com.org.dto.BookRequest;

public interface IBookService {

	
	public String saveBook(BookRequest request);
	
	public BookRequest getByBookId(Integer id);
	
	public List<BookRequest> getAllBooks();
	
	public void deleteBook(Integer id);
	
	
	
}
