package com.messenger.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.messenger.domain.Message;
import com.messenger.repositories.MessageRepo;

@Controller
public class ViewMessages {
	
	@Autowired
	MessageRepo messageRepo;
	
	@GetMapping("/messages")
	public String getMessages(ModelMap model) {
		
		List<Message> listOfMessages = messageRepo.findAll();
			model.put("messages",listOfMessages );
		return "messages";
	
	}
	
	@PostMapping("/messages")
	public String saveMessages(@RequestParam String text, @RequestParam String tag) {
		
		Message newMessage = new Message();
		newMessage.setText(text);
		newMessage.setTag(tag);
		messageRepo.save(newMessage);
		return "messages";
		
	}
	
	@PostMapping("/filter")
	public String filter(ModelMap model, @RequestParam(required = false) String filter) {
		List<Message> list;
		if(!filter.isEmpty()) {
			list=messageRepo.findByTag(filter);
		}else {
			list=messageRepo.findAll();
			  }
		model.put("messages", list);
		return "messages";
		
	}


}
