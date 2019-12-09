package com.mgosciminski.library.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgosciminski.library.dto.BookRentDto;
import com.mgosciminski.library.dto.UserCreatorDto;
import com.mgosciminski.library.dto.UserDisplayDto;
import com.mgosciminski.library.dto.UserEditDto;
import com.mgosciminski.library.service.UserService;

import javassist.NotFoundException;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping(value = "users")
	public ResponseEntity<List<UserDisplayDto>> displayUsers()
	{
		List<UserDisplayDto> userDisplayDtos = userService.findAllUsersDisplayDto();
		
		return new ResponseEntity<List<UserDisplayDto>>(userDisplayDtos,HttpStatus.OK);
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
	
	@GetMapping(value = "/user")
	public ResponseEntity<UserDisplayDto> findUser(@RequestParam String id ) throws NotFoundException
	{
		
		UserDisplayDto userDisplayDto = userService.findUserDisplayDtoById(Long.valueOf(id));
		
		return new ResponseEntity<UserDisplayDto>(userDisplayDto,HttpStatus.OK);
	}
	
	@PostMapping(value = "user/{id}/givebackbook")
	public ResponseEntity<UserDisplayDto> giveBackBooks(@PathVariable String id, @Valid @RequestBody BookRentDto bookRentDto) throws NotFoundException
	{
		UserDisplayDto userDisplayDto = userService.giveBackBooks(Long.valueOf(id), bookRentDto);
		
		return new ResponseEntity<UserDisplayDto>(userDisplayDto,HttpStatus.OK);
	}
	
	@PostMapping(value = "user/{id}/edit")
	public ResponseEntity<UserDisplayDto> editUser(@PathVariable String id, @Valid @RequestBody UserEditDto userEditDto) throws NumberFormatException, NotFoundException
	{
		UserDisplayDto userDisplayDto = userService.editUser(Long.valueOf(id), userEditDto);
		
		return new ResponseEntity<UserDisplayDto>(userDisplayDto,HttpStatus.ACCEPTED);
	}
	
}
