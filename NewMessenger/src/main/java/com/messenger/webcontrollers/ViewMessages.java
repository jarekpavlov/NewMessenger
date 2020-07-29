package com.messenger.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.messenger.domain.Message;
import com.messenger.repositories.MessageRepo;

@Controller
public class ViewMessages {
	
	@Autowired
	MessageRepo message;
	
	@GetMapping("/messages")
	public String getMessages() {
		
		return "messages";
	
	}
	
	@PostMapping("/messages")
	public String saveMessages(@RequestParam String text, @RequestParam String tag) {
		
		Message newMessage = new Message();
		newMessage.setText(text);
		newMessage.setTag(tag);
		
		message.save(newMessage);
		
		return "messages";
		
	}

}
