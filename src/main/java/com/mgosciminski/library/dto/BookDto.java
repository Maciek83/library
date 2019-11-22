package com.mgosciminski.library.dto;

import io.micrometer.core.lang.NonNull;

public class BookDto {
	
	@NonNull
	private String name;
	@NonNull
	private Integer number;
	
	public BookDto() {
		super();
	}
	
	public BookDto(String name, Integer number) {
		super();
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
}
