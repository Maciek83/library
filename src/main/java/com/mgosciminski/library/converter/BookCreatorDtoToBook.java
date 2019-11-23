package com.mgosciminski.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.BookDto;
import com.mgosciminski.library.model.Book;
import com.mgosciminski.library.model.Quantity;

@Component
public class BookDtoToBook implements Converter<BookDto, Book> {

	@Override
	public Book convert(BookDto source) {
		
		Book newBook = new Book();
		newBook.setTitle(source.getName());
		Quantity newQuantity = new Quantity();
		newQuantity.setNumber(source.getNumber());
		newBook.setQuantity(newQuantity);
		newQuantity.setBook(newBook);
		
		return newBook;
	}

}
