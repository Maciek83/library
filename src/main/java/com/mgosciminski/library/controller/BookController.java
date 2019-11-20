package com.mgosciminski.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.service.BookService;

@Controller
public class BookController {

	private final BookService bookService;
	
	
	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}



	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> showAllBooks(){
		
		List<Book> books = bookService.findAllBooks();
		
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/ok")
	public ResponseEntity<String> isok()
	{
		return new ResponseEntity<String>("login",HttpStatus.OK);
	}
	
}
