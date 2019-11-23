package com.mgosciminski.library.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.BookDisplayDto;
import com.mgosciminski.library.dto.UserDisplayDto;
import com.mgosciminski.library.model.User;

@Component
public class UserToUserDisplayDto implements Converter<User, UserDisplayDto>{

	private final BookToBookDisplayDto bookToBookDisplayDtoConverter;
	
	public UserToUserDisplayDto(BookToBookDisplayDto bookToBookDisplayDtoConverter) {
		super();
		this.bookToBookDisplayDtoConverter = bookToBookDisplayDtoConverter;
	}

	@Override
	public UserDisplayDto convert(User source) {
		
		UserDisplayDto userDisplayDto = new UserDisplayDto();
		userDisplayDto.setId(source.getId());
		userDisplayDto.setName(source.getName());
		userDisplayDto.setActive(source.getActive());
		
	
		Set<String> roles = source.getRoles()
				.stream()
				.map(r-> r.getRole().toString()).collect(Collectors.toSet());
		
		userDisplayDto.setRoles(roles);

		Set<BookDisplayDto> bookDisplayDtos = source.getBook()
				.stream()
				.map(bookToBookDisplayDtoConverter::convert).collect(Collectors.toSet());
		
		userDisplayDto.setBooks(bookDisplayDtos);
		
		return userDisplayDto;
	}

	
	
}
