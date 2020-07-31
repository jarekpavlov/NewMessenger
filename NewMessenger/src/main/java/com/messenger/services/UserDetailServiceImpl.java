package com.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.messenger.domain.User;
import com.messenger.repositories.UserRepo;
import com.messenger.security.CustomSequrityUser;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		if (user==null)
			throw new UsernameNotFoundException("Invalid Username and password");
		
		return new CustomSequrityUser(user);
	}

}
