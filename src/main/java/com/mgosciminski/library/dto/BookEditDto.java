package com.mgosciminski.library.dto;

import io.micrometer.core.lang.NonNull;

public class BookEditDto {
	
	@NonNull
	private String title;
	@NonNull
	private Integer quantity;
	
	public BookEditDto() {
		super();
	}

	public BookEditDto(String title, Integer quantity) {
		super();
		this.title = title;
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
