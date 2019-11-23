package com.mgosciminski.library.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
