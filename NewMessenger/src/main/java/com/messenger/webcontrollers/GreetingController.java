package com.messenger.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {
	
	@GetMapping("/")
	public  String getGreetingPage(@RequestParam(name="name",required = false, defaultValue = "User") String name, ModelMap model) {
		
		model.put("name", name);
		return "greetingpage";
		
	}

}
