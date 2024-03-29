package com.mgosciminski.library.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;
import com.mgosciminski.library.repository.RoleRepository;

@Service("roleService")
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	
	public Role saveRole(String role)
	{
		Role r = findByRole(role);
		
		if (r == null) 
		{
			r = new Role();
			r.setRole(RoleType.valueOf(role));
			return roleRepository.save(r);
		}
		
		return r;
		
	}
	
	public Set<Role> findRolesByStrings(Set<String> names)
	{
		Set<Role> roles = new HashSet<Role>();
		
		names.stream().forEach(n->{
			roles.add(findByRole(n)) ;
		});
		
		return roles;
	}
	
	
	
	public Role findByRole(String role) {
		
		return roleRepository.findByRole(RoleType.valueOf(role));
	}
}
