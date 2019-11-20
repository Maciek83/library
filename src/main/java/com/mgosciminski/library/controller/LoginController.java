package com.mgosciminski.library.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mgosciminski.library.model.User;
import com.mgosciminski.library.service.UserService;


@Controller
public class LoginController {

	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@GetMapping(value = "/registration")
	public ResponseEntity<User> registration()
	{
		return new ResponseEntity<>(new User(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/registration")
	public ResponseEntity<User> createNewUser(@Valid User user, BindingResult bindingResult)
	{
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if (bindingResult.hasErrors()) {
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		if(userExists != null)
		{
			return new ResponseEntity<User>(userExists,HttpStatus.ACCEPTED);
		}
		else 
		{	
			User savedUser = userService.saveUser(user);
			
			return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "/admin/home")
	public ResponseEntity<User> home()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(authentication.getName());
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
}
