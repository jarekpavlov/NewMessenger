package com.messenger.webcontrollers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.messenger.domain.RolesBoolean;
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

		RolesBoolean rolesBoolean = new RolesBoolean();
		rolesBoolean.setUserRole(false);
		rolesBoolean.setAdminRole(false);
		
		for (Authority authority:authoritys) {
			String authorityStr = authority.getAuthority();
			
			if (authorityStr.equals(roleUser)) {
				rolesBoolean.setUserRole(true);
			}
			if (authorityStr.equals(roleAdmin)) {
				rolesBoolean.setAdminRole(true);
			}
		}
		model.put("rolesBoolean", rolesBoolean);
		model.put("user", user);
		
		return "useredit";
	}
	@PostMapping("/users/{userId}")
	public String saveUser( @PathVariable Long userId, User user, RolesBoolean rolesBoolean ) {
		
		
		Authority userRole = new Authority();
		Authority adminRole = new Authority();

		if(rolesBoolean.isUserRole()) {
			userRole.setAuthority("ROLE_USER");
			userRole.setUser(user);
			user.getAuthorities().add(userRole);
		}
		if(rolesBoolean.isAdminRole()) {
			adminRole.setAuthority("ROLE_ADMIN");
			adminRole.setUser(user);
			user.getAuthorities().add(adminRole);
		}
		
		Optional <User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			User userTemp = userOpt.get();
			userTemp.getAuthorities().clear();
			userRepo.save(userTemp);
		}
		userRepo.save(user);
		
		return "redirect:/users";
	}
	
	
	

}
