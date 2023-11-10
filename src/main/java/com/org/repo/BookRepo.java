package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

}
