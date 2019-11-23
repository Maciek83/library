package com.mgosciminski.library.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mgosciminski.library.dto.UserCreatorDto;
import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;
import com.mgosciminski.library.model.User;

@Component
public class UserCreatorDtoToUser implements Converter<UserCreatorDto, User> {

	@Override
	public User convert(UserCreatorDto source) {
		
		User newUser = new User();
		newUser.setName(source.getName());
		newUser.setEmail(source.getEmail());
		newUser.setActive(source.getActive());
		newUser.setPassword(source.getPassword());
		
		Set<Role> roles = new HashSet<Role>();
		
		source.getRoles().stream().forEach(r->{
			
			Role role = new Role();
			role.setRole(RoleType.valueOf(r));
			roles.add(role);
			
		});
		
		newUser.setRoles(roles);
		
		return newUser;
	}

	
}
