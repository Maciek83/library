package com.mgosciminski.library.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mgosciminski.library.model.Role;
import com.mgosciminski.library.model.RoleType;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.RoleRepository;
import com.mgosciminski.library.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository mockUserRepository;
	@Mock
	private RoleRepository mockRoleRepository;
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	
	@InjectMocks
	private UserService userServiceUnderTest;
	
	private User user;
	
	@BeforeEach
	public void setUp()
	{
		
		user = new User();
		user.setId(1L);
		user.setName("Maciek");
		user.setEmail("m.gosciminski@wp.pl");
		
	}
	
	@Test
	public void testFindUserByEmail() {
		
		//setup
		final String email = "m.gosciminski@wp.pl";
		when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
		
		//run the test
		final User result = userServiceUnderTest.findUserByEmail(email);
		
		//Verify the result
		assertEquals(email, result.getEmail());
		
	}
	
	@Test
	public void testSaveUser() {
		
		//setup
		
		User user = new User();
		
		when(mockRoleRepository.findByRole(any())).thenReturn(new Role(RoleType.ADMIN, new HashSet<User>()));
		when(mockUserRepository.save(any(User.class))).thenReturn(user);
		//run the test
		User result = userServiceUnderTest.saveUser(new User());
		
		// verify the result
		assertEquals(user, result);
	}

}
