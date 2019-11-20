package com.mgosciminski.library.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.UserRepository;

@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleService = roleService;
	}
	
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public User findUserByName(String name)
	{
		return userRepository.findByName(name);
	}
	
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> rolesOld = user.getRoles();
		user.setRoles(new HashSet<Role>());
		
		User fromDb = userRepository.save(user);
		
		Set<Role> rolesNew = new HashSet<Role>();
		
		for (Role r : rolesOld) {
			
			Role fromDataBaseRole = roleService.saveRole(r.getRole().toString());
			
			rolesNew.add(fromDataBaseRole);
		}
		
		fromDb.setRoles(rolesNew);
		
		return userRepository.save(fromDb);
		
	}
	
}
