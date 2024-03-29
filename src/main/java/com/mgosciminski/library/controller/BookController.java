package com.mgosciminski.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgosciminski.library.dto.BookCreatorDto;
import com.mgosciminski.library.dto.BookDisplayDto;
import com.mgosciminski.library.dto.BookEditDto;
import com.mgosciminski.library.service.BookService;

import javassist.NotFoundException;

@Controller
public class BookController {

	private final BookService bookService;

	
	
	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}



	@GetMapping(value = "/book")
	public ResponseEntity<List<BookDisplayDto>> showAllBooksDto(
			@RequestParam String page, 
			@RequestParam String number){
		
		
		List<BookDisplayDto> books = bookService.findBooksPage(Integer.valueOf(page), Integer.valueOf(number));
		
		return new ResponseEntity<List<BookDisplayDto>>(books,HttpStatus.OK);
		
	}
	
	@PostMapping(value="/book/new")
	public ResponseEntity<BookDisplayDto> addNewBook(@Valid @RequestBody BookCreatorDto bookDto)
	{
		BookDisplayDto bookDisplayDto = bookService.saveBookFromBookCreatorDto(bookDto);
		
		return new ResponseEntity<BookDisplayDto>(bookDisplayDto,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/book/{id}/edit")
	public ResponseEntity<BookDisplayDto> editBook(@PathVariable String id, @Valid @RequestBody BookEditDto bookEditDto) throws NumberFormatException, NotFoundException
	{
		BookDisplayDto bookDisplayDto = bookService.editBookNumber(Long.valueOf(id), bookEditDto);
		
		return new ResponseEntity<BookDisplayDto>(bookDisplayDto,HttpStatus.ACCEPTED);
	}
	
}
