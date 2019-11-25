package com.mgosciminski.library.dto;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.lang.NonNull;

public class BookRentDto 
{
	@NonNull
	private List<Long> indexsOfBooks = new ArrayList<>();

	public BookRentDto() {
		super();
	}

	public BookRentDto(List<Long> indexsOfBooks) {
		super();
		this.indexsOfBooks = indexsOfBooks;
	}

	public List<Long> getIndexsOfBooks() {
		return indexsOfBooks;
	}

	public void setIndexsOfBooks(List<Long> indexsOfBooks) {
		this.indexsOfBooks = indexsOfBooks;
	}
	
	
}
