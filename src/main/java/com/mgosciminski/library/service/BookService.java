package com.mgosciminski.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.converter.BookDtoToBook;
import com.mgosciminski.library.converter.BookToBookDto;
import com.mgosciminski.library.dto.BookDto;
import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final BookDtoToBook bookDtoToBookConverter;
	private final BookToBookDto bookToBookDtoConverter;

	@Autowired
	public BookService(BookRepository bookRepository, BookDtoToBook bookDtoToBookConverter,
			BookToBookDto bookToBookDtoConverter) {
		super();
		this.bookRepository = bookRepository;
		this.bookDtoToBookConverter = bookDtoToBookConverter;
		this.bookToBookDtoConverter = bookToBookDtoConverter;
	}
	
	
	public List<Book> findAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public List<BookDto> findAllBooksDto()
	{
		return bookRepository.findAll()
				.stream()
				.map(this::convertBookToBookDto)
				.collect(Collectors.toList());
	}

	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public Book saveBookFromDto(BookDto bookDto)
	{
		return bookRepository.save(convertBookDtoToBook(bookDto));
	}
	
	public Book convertBookDtoToBook(BookDto bookDto)
	{
		return bookDtoToBookConverter.convert(bookDto);
	}
	
	public BookDto convertBookToBookDto(Book book)
	{
		return bookToBookDtoConverter.convert(book);
	}
	
	
}
