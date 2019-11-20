package com.mgosciminski.library.repository;

import org.springframework.stereotype.Repository;

import com.mgosciminski.library.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,Long>{
	
}
