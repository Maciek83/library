package com.mgosciminski.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.BookDto;
import com.mgosciminski.library.model.Book;

@Component
public class BookToBookDto implements Converter<Book, BookDto> {

	@Override
	public BookDto convert(Book source) {
		
		BookDto newBookDto = new BookDto();
		newBookDto.setName(source.getTitle());
		newBookDto.setNumber(source.getQuantity().getNumber());
		
		return newBookDto;
	}

}
