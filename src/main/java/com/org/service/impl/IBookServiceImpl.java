package com.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.Book;
import com.org.dto.BookRequest;
import com.org.exception.IdNotFoundException;
import com.org.repo.BookRepo;
import com.org.service.IBookService;

@Service
public class IBookServiceImpl implements IBookService {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public String saveBook(BookRequest request) {
		Book book = new Book();
		BeanUtils.copyProperties(request, book);
		bookRepo.save(book);
		return "Success";
	}

	@Override
	public BookRequest getByBookId(Integer id) {
		Optional<Book> optional = bookRepo.findById(id);
		if (optional.isPresent()) {
			Book book = optional.get();
			BookRequest request = new BookRequest();
			BeanUtils.copyProperties(book, request);
			return request;
		}
		throw new IdNotFoundException("Id not foundException ::" + id);
	}

	@Override
	public List<BookRequest> getAllBooks() {
		List<Book> list = bookRepo.findAll();
		return list.stream().map(entity -> {
			BookRequest request = new BookRequest();
			BeanUtils.copyProperties(entity, request);
			return request;
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteBook(Integer id) {
		Optional<Book> bookId = bookRepo.findById(id);
		if (!bookId.isPresent()) {
			throw new IdNotFoundException("Id not foundException ::" + id);
		}
		bookRepo.deleteById(id);

	}

}
