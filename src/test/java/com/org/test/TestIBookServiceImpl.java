package com.org.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.org.domain.Book;
import com.org.dto.BookRequest;
import com.org.exception.IdNotFoundException;
import com.org.repo.BookRepo;
import com.org.service.impl.IBookServiceImpl;

@SpringBootTest
public class TestIBookServiceImpl {

	@Mock
	private BookRepo bookRepo;

	@InjectMocks
	private IBookServiceImpl ibookServiceImpl;

	@Test
	void testSaveBook() {

		BookRequest request = new BookRequest(1, "siva", 88.9);
		Book entity = new Book();
		BeanUtils.copyProperties(request, entity);
		when(bookRepo.save(entity)).thenReturn(entity);
		assertEquals(ibookServiceImpl.saveBook(request), "Success");
	}

	@Test
	void testNullSaveBook() {
		BookRequest request = new BookRequest();
		Book entity = new Book();
		BeanUtils.copyProperties(request, entity);
		assertEquals(bookRepo.save(entity), null);
	}

	@Test
	void testGetBookById() {
		BookRequest request = new BookRequest(1, "siva", 88.9);
		Book entity = new Book();
		BeanUtils.copyProperties(request, entity);
		when(bookRepo.findById(1)).thenReturn(Optional.of(entity));

		assertEquals(ibookServiceImpl.getByBookId(1), request);

	}

	@Test
	void testIdNotFoundException() {
		IdNotFoundException ex = assertThrows(IdNotFoundException.class, () -> {
			ibookServiceImpl.getByBookId(87);
		});
		assertEquals("Id not foundException ::" + 87, ex.getMessage());
	}

	@Test
	void testGetAllBooks() {
		List<Book> list = new ArrayList<>();
		list.add(new Book(1, "Shasha", 99.9));
		list.add(new Book(2, "Siva", 97.9));
		when(bookRepo.findAll()).thenReturn(list);
		List<BookRequest> allBooks = ibookServiceImpl.getAllBooks();
		assertEquals(allBooks.size(), list.size());

	}

	@Test
	void testDeleteBookWithInvalidId() {
	    // Arrange
	    Integer invalidId = 87;
	    when(bookRepo.findById(invalidId)).thenReturn(Optional.empty());

	    // Act and Assert
	    IdNotFoundException ex = assertThrows(IdNotFoundException.class, () -> {
	        ibookServiceImpl.deleteBook(invalidId);
	    });

	    assertEquals("Id not foundException ::" + invalidId, ex.getMessage());
	    verify(bookRepo).deleteById(invalidId); // Ensure deleteById is not called
	}
	@Test
	void testDeleteBook() {
	    // Arrange
	    Integer id = 1;
	    Book entity = new Book(id, "siva", 88.9);
	    when(bookRepo.findById(id)).thenReturn(Optional.of(entity));
	    ibookServiceImpl.deleteBook(id);
	    verify(bookRepo).deleteById(id);
	}


}
