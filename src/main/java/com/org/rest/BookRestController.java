package com.org.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.BookRequest;
import com.org.service.IBookService;

@RestController
@RequestMapping("/bookapi")
public class BookRestController {

	@Autowired
	private IBookService ibookService;

	@PostMapping("/save")
	public ResponseEntity<?> saveBook(@RequestBody BookRequest request) {
		String saveBook = ibookService.saveBook(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveBook);
	}

	@GetMapping("/getBook/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id) {
		BookRequest bookId = ibookService.getByBookId(id);
		return ResponseEntity.status(HttpStatus.OK).body(bookId);
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<?> getAllBooks() {
		List<BookRequest> dtos = ibookService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
0.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable Integer id) {
		ibookService.deleteBook(id);
		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
