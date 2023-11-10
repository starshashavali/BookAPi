package com.org.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.dto.BookRequest;
import com.org.service.IBookService;

@WebMvcTest
public class TestBookRestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IBookService bookService;

	private static String asJsonString(final Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	@Test
	void testSave() throws Exception {
		BookRequest request = new BookRequest(1, "Siva", 99.9);
		when(bookService.saveBook(request)).thenReturn("Success");
		mockMvc.perform(post("/bookapi/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
				.andExpect(status().isCreated());
	}

	@Test
	void testgetBookById() throws Exception {
		BookRequest request = new BookRequest(1, "Siva", 99.9);

		when(bookService.getByBookId(1)).thenReturn(request);
		mockMvc.perform(get("/bookapi/getBook/{id}", 1))
		.andExpect(status().isOk());
	}

	@Test
	void testgetAllBooks() throws Exception {
		
	       List<BookRequest> list = Arrays.asList(
	                new BookRequest(1, "Siva", 99.9),
	                new BookRequest(2, "Shankar", 69.9)
	        );

	        when(bookService.getAllBooks()).thenReturn(list);

	        mockMvc.perform(get("/bookapi/getAllBooks"))
	                .andExpect(status().isOk());
	              
	    }
	@Test
	void testDeleteBook() throws Exception {
	       Integer id = 1;
	        mockMvc.perform(delete("/bookapi/{id}", id))
	                .andExpect(status().isOk());
	        verify(bookService,times(1)).deleteBook(id);
	    }
	}

