package com.mgosciminski.library.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quantity")
public class Quantity extends BaseEntity{

	private Integer number;
	
	@OneToOne(mappedBy = "quantity")
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private Book book;
	

	public Quantity() {
		super();
	}

	public Quantity(Integer number, Book book) {
		super();
		this.number = number;
		this.book = book;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
}
