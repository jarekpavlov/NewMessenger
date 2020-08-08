package com.messenger.webcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.messenger.domain.User;
import com.messenger.repositories.UserRepo;

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
		model.put("user", user);
		return "useredit";
	}
	
	
	

}
