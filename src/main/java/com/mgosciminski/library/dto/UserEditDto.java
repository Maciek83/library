package com.mgosciminski.library.dto;

import java.util.HashSet;
import java.util.Set;

import io.micrometer.core.lang.NonNull;


public class UserEditDto {
	

	@NonNull
	private int active;
	@NonNull
	private Set<String> roles = new HashSet<>();
	
	public UserEditDto() {
		super();
	}

	public UserEditDto(int active, Set<String> roles) {
		super();
		this.active = active;
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
}
