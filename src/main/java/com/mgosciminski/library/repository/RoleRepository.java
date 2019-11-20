package com.mgosciminski.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRole(RoleType role);
}
