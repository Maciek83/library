package com.mgosciminski.library.dto;

public class BookDisplayDto {

	private Long id;
	private String title;
	private Integer quantity;
	
	public BookDisplayDto() {
		super();
	}

	public BookDisplayDto(Long id, String title, Integer quantity) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
