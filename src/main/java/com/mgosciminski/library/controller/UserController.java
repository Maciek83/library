package com.mgosciminski.library.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mgosciminski.library.dto.UserDto;
import com.mgosciminski.library.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value = "user/new")
	public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
		
		userService.saveUserDto(userDto);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
}
