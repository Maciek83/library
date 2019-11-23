package com.mgosciminski.library.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.converter.UserCreatorDtoToUser;
import com.mgosciminski.library.converter.UserToUserDisplayDto;
import com.mgosciminski.library.dto.UserCreatorDto;
import com.mgosciminski.library.dto.UserDisplayDto;
import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.UserRepository;

@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	private final UserCreatorDtoToUser userCreatorDtoToUserConverter;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserToUserDisplayDto userToUserDisplayDtoConverter;
	
	@Autowired
	public UserService(UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, 
			RoleService roleService,
			UserCreatorDtoToUser userCreatorDtoToUser,
			UserToUserDisplayDto userToUserDisplayDtoConverter) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleService = roleService;
		this.userCreatorDtoToUserConverter = userCreatorDtoToUser;
		this.userToUserDisplayDtoConverter = userToUserDisplayDtoConverter;
	}
	
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public User findUserByName(String name)
	{
		return userRepository.findByName(name);
	}
	
	public UserDisplayDto saveUserDto(UserCreatorDto userCreatorDto)
	{
		User newUser = userCreatorDtoToUserConverter.convert(userCreatorDto);
		
		return saveUser(newUser);
	}
	
	public UserDisplayDto saveUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> rolesOld = user.getRoles();
		user.setRoles(new HashSet<Role>());
		
		User fromDb = userRepository.save(user);
		
		Set<Role> rolesNew = new HashSet<>();
		
		for (Role r : rolesOld) {
			
			Role fromDataBaseRole = roleService.saveRole(r.getRole().toString());
			
			rolesNew.add(fromDataBaseRole);
		}
		
		fromDb.setRoles(rolesNew);
		
		
		return userToUserDisplayDtoConverter.convert(userRepository.save(fromDb)) ;
		
	}
	
}
