package com.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.messenger.domain.User;
import com.messenger.repositories.UserRepo;
import com.messenger.security.Authority;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User user) {
		
		String password = user.getPassword();
		String encodedPassword=passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		
		Authority authority =new Authority();
		authority.setUser(user);
		authority.setAuthority("ROLE_USER");
		user.getAuthorities().add(authority);
		return userRepo.save(user);
		
	}

}
