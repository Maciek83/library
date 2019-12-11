package com.mgosciminski.library.repository;

import org.springframework.stereotype.Repository;

import com.mgosciminski.library.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository("bookRepository")
public interface BookRepository extends PagingAndSortingRepository<Book,Long>{
	
}
