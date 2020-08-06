package com.messenger.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		return "editingusers";
	}
	

}
