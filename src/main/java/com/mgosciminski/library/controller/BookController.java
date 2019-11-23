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

import com.mgosciminski.library.dto.BookCreatorDto;
import com.mgosciminski.library.dto.BookDisplayDto;
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
	public ResponseEntity<List<BookDisplayDto>> showAllBooksDto(){
		
		List<BookDisplayDto> books = bookService.findAllBooksDisplayDto();
		
		return new ResponseEntity<List<BookDisplayDto>>(books,HttpStatus.OK);
		
	}
	
	@PostMapping(value="/book/new")
	public ResponseEntity<BookDisplayDto> addNewBook(@Valid @RequestBody BookCreatorDto bookDto)
	{
		BookDisplayDto bookDisplayDto = bookService.saveBookFromBookCreatorDto(bookDto);
		
		return new ResponseEntity<BookDisplayDto>(bookDisplayDto,HttpStatus.CREATED);
	}
	
}
