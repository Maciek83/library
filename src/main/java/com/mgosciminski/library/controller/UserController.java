package com.mgosciminski.library.controller;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mgosciminski.library.dto.BookRentDto;
import com.mgosciminski.library.dto.UserCreatorDto;
import com.mgosciminski.library.dto.UserDisplayDto;
import com.mgosciminski.library.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value = "user/new")
	public ResponseEntity<UserDisplayDto> createNewUser(@Valid @RequestBody UserCreatorDto userCreatorDto){
		
		UserDisplayDto s = userService.saveUserDto(userCreatorDto);
		
		return new ResponseEntity<UserDisplayDto>(s,HttpStatus.CREATED);
	}
	
	@PostMapping(value="user/rentbook")
	public ResponseEntity<UserDisplayDto> rentBooks(Authentication authentication, @Valid @RequestBody BookRentDto bookRentDto)
	{
		UserDisplayDto userDisplayDto = userService.rentBooks(authentication.getName(), bookRentDto);
		
		return new ResponseEntity<UserDisplayDto>(userDisplayDto,HttpStatus.OK);
	}
}
