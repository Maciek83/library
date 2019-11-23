package com.mgosciminski.library.converter;

import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.UserDto;
import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;
import com.mgosciminski.library.model.User;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

@Component
public class UserDtoToUser implements Converter<UserDto, User>{

	@Override
	public User convert(UserDto source) {
		
		User newUser = new User();
		
		newUser.setName(source.getName());
		newUser.setActive(source.getActive());
		newUser.setEmail(source.getEmail());
		newUser.setPassword(source.getPassword());
		
		Set<Role> roles = new HashSet<>();
		source.getRoles().stream().forEach(r-> {
			
			Role role = new Role();
			role.setRole(RoleType.valueOf(r));
			roles.add(role);
		});
		
		newUser.setRoles(roles);
		
		return newUser;
	}

	
}
