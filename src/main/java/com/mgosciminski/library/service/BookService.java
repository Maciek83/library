package com.mgosciminski.library.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.converter.BookCreatorDtoToBook;
import com.mgosciminski.library.converter.BookToBookDisplayDto;
import com.mgosciminski.library.dto.BookCreatorDto;
import com.mgosciminski.library.dto.BookDisplayDto;
import com.mgosciminski.library.dto.BookRentDto;
import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.repository.BookRepository;

import javassist.NotFoundException;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final BookCreatorDtoToBook bookDtoToBookConverter;
	private final BookToBookDisplayDto bookToBookDisplayDtoConverter;

	@Autowired
	public BookService(BookRepository bookRepository, 
			BookCreatorDtoToBook bookCreatorDtoToBookConverter,
			BookToBookDisplayDto bookToBookDisplayDtoConverter) {
		super();
		this.bookRepository = bookRepository;
		this.bookDtoToBookConverter = bookCreatorDtoToBookConverter;
		this.bookToBookDisplayDtoConverter = bookToBookDisplayDtoConverter;
	}
	
	
	public List<Book> findAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public List<BookDisplayDto> findAllBooksDisplayDto()
	{
		return bookRepository.findAll()
				.stream()
				.map(this::convertBookToBookDisplayDto)
				.collect(Collectors.toList());
	}
	
	public Book findBookById(Long id) throws NotFoundException
	{
		return bookRepository
				.findById(id)
				.orElseThrow(()-> new NotFoundException("Book with this id don't exist."));
	}
	
	public Set<Book> findBooksToRent(BookRentDto bookRentDto)
	{
		Set<Book> books = new HashSet<Book>();
		
		bookRentDto.getIndexsOfBooks()
		.stream()
		.forEach(i->{
			try {
				Book book = findBookById(i);
				books.add(book);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		
		return books;
	}

	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public BookDisplayDto saveBookFromBookCreatorDto(BookCreatorDto bookDto)
	{
		return convertBookToBookDisplayDto(saveBook(convertBookCreatorDtoToBook(bookDto)));
	}
	
	public Book convertBookCreatorDtoToBook(BookCreatorDto bookDto)
	{
		return bookDtoToBookConverter.convert(bookDto);
	}
	public BookDisplayDto convertBookToBookDisplayDto(Book book)
	{
		return bookToBookDisplayDtoConverter.convert(book);
	}
	
}
