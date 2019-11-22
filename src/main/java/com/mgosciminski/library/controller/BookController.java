package com.mgosciminski.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mgosciminski.library.dto.BookDto;
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



	@GetMapping(value = "/book")
	public ResponseEntity<List<BookDto>> showAllBooksDto(){
		
		List<BookDto> books = bookService.findAllBooksDto();
		
		return new ResponseEntity<List<BookDto>>(books,HttpStatus.OK);
		
	}
	
	@PostMapping(value="/book/new")
	public ResponseEntity<BookDto> addNewBook(@Valid @RequestBody BookDto bookDto)
	{
		bookService.saveBookFromDto(bookDto);
		
		return new ResponseEntity<BookDto>(bookDto,HttpStatus.CREATED);
	}
	
}
