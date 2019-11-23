package com.mgosciminski.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.BookDisplayDto;
import com.mgosciminski.library.model.Book;

@Component
public class BookToBookDisplayDto implements Converter<Book, BookDisplayDto> {

	@Override
	public BookDisplayDto convert(Book source) {
		
		BookDisplayDto bookDisplayDto = new BookDisplayDto(source.getId(),source.getTitle(),source.getQuantity().getNumber());
		
		return bookDisplayDto;
	}

}
