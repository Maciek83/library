package com.mgosciminski.library.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDisplayDto {
	
	private Long id;
	private String name;
	private int active;
	private Set<String> roles = new HashSet<String>();
	private Set<BookDisplayDto> books = new HashSet<BookDisplayDto>();
	
	public UserDisplayDto() {
		super();
	}

	public UserDisplayDto(Long id, String name, int active, Set<String> roles, Set<BookDisplayDto> books) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.roles = roles;
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<BookDisplayDto> getBooks() {
		return books;
	}

	public void setBooks(Set<BookDisplayDto> books) {
		this.books = books;
	}
	
	
}
