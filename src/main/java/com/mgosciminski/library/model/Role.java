package com.mgosciminski.library.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private RoleType role;

	public Role() {
		super();
	}

	public Role(RoleType role) {
		super();
		this.role = role;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}
	
	
}
