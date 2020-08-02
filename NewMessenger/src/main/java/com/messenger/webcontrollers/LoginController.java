package com.messenger.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.messenger.domain.User;
import com.messenger.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String LogIn() {
		
		return "login";
		
	}
	@GetMapping("/registration")
	public String Registration(ModelMap model) {
		
		model.put("user", new User());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String SaveUser(User user) {
		
		userService.save(user);
		return "redirect:login";
	}
	
}
