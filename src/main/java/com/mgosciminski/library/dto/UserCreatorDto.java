package com.mgosciminski.library.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import io.micrometer.core.lang.NonNull;

public class UserCreatorDto {
	
	@NonNull
	private String name;
	@NonNull
	@Email
	private String email;
	@NonNull
	private String password;
	@NonNull
	private Set<String> roles = new HashSet<>();
	
	public UserCreatorDto() {
		super();
	}

	

	public UserCreatorDto(String name, String email, String password, Set<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
