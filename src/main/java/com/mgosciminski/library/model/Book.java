package com.mgosciminski.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "count")
	private Integer count;

	public Book() {
		super();
	}

	public Book(String title, Integer count) {
		super();
		this.title = title;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	
}
