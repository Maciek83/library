package com.mgosciminski.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {
	
	@Column(name = "title")
	private String title;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "quantity_id", referencedColumnName = "id")
	private Quantity quantity;


	public Book() {
		super();
	}


	public Book(String title, Quantity quantity) {
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


	public Quantity getQuantity() {
		return quantity;
	}


	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	
	
	
}
