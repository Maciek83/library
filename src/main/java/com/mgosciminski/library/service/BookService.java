package com.mgosciminski.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	
	public List<Book> findAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	
}
