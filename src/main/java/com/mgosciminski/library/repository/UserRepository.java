package com.mgosciminski.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgosciminski.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	User findByName(String name);
}
