package com.mgosciminski.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgosciminski.library.model.PdfUserDetails;
import com.mgosciminski.library.model.User;
import com.mgosciminski.library.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByName(username);
		
		
		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}
		else {
			
			PdfUserDetails pdfUserDetails = new PdfUserDetails(user);
			
			return pdfUserDetails;
		}
		
		
	}

	

}
