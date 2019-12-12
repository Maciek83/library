package com.mgosciminski.library.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.converter.BookCreatorDtoToBook;
import com.mgosciminski.library.converter.BookToBookDisplayDto;
import com.mgosciminski.library.dto.BookCreatorDto;
import com.mgosciminski.library.dto.BookDisplayDto;
import com.mgosciminski.library.dto.BookEditDto;
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
	
	
	public Iterable<Book> findAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public List<BookDisplayDto> findBooksPage(int site, int elements)
	{
		Pageable pageable = PageRequest.of(site, elements);
		List<BookDisplayDto> page = bookRepository
				.findAll(pageable)
				.stream()
				.map(this::convertBookToBookDisplayDto)
				.collect(Collectors.toList());
		
		return page;
	}
	
	public BookDisplayDto editBookNumber(Long id, BookEditDto bookEditDto) throws NotFoundException
	{
		Book bookFromDB = findBookById(id);
		
		bookFromDB.getQuantity().setNumber(bookEditDto.getQuantity());
		bookFromDB.setTitle(bookEditDto.getTitle());

		Book saved = saveBook(bookFromDB);
		
		return convertBookToBookDisplayDto(saved);
	}
	
	public List<BookDisplayDto> findAllBooksDisplayDto()
	{
		return ((Collection<Book>) bookRepository.findAll())
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
