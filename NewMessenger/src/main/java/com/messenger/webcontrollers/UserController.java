package com.messenger.webcontrollers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.messenger.domain.User;
import com.messenger.repositories.UserRepo;
import com.messenger.security.Authority;

@Controller
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	
	@GetMapping("/users")
	public String getUsers(ModelMap model) {
		
		List<User> allUsers =userRepo.findAll();
		model.put("users", allUsers);		
		
		return "userinfo";
	}
	
	@GetMapping("/users/{userId}")
	public String changeUser(ModelMap model, @PathVariable Long userId) {

		Optional <User> userOpt=userRepo.findById(userId);
		User user = new User();
		if (userOpt.isPresent()) {
			user = userOpt.get();
			}

		Set<Authority> authoritys = user.getAuthorities();
		
		String roleUser = "ROLE_USER";
		String roleAdmin = "ROLE_ADMIN";
		boolean hasUserRole= false;
		boolean hasAdminRole = false;
		
		for (Authority authority:authoritys) {
			String authorityStr = authority.getAuthority();
			
			if (authorityStr.equals(roleUser)) {
				hasUserRole=true;
			}
			
			if (authorityStr.equals(roleAdmin)) {
				hasAdminRole=true;
			}
			
		}
		System.out.println(hasUserRole);
		System.out.println(hasAdminRole);
		

		model.put("userRole", hasUserRole);
		model.put("adminRole", hasAdminRole);
		model.put("user", user);
		return "useredit";
	}
	
	
	

}
