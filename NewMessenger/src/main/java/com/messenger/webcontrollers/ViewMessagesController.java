package com.messenger.webcontrollers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.messenger.domain.Message;
import com.messenger.domain.User;
import com.messenger.repositories.MessageRepo;

@Controller
public class ViewMessagesController {
	
    @Value("${upload.path}")
    String uploadPath;
    
	@Autowired
	MessageRepo messageRepo;
	
	@GetMapping("/messages")
	public String getMessages(ModelMap model) {
		
		List<Message> listOfMessages = messageRepo.findAll();
			model.put("messages",listOfMessages );
		return "messages";
	
	}
	
	@PostMapping("/messages")
	public String saveMessages(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, @RequestParam("file") MultipartFile file) throws  IOException {
		
		Message newMessage = new Message();
		newMessage.setText(text);
		newMessage.setTag(tag);
		
		if(file!=null) {
			File uploadDir=new File(uploadPath);
			
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile=UUID.randomUUID().toString();
			String resultFileName=uuidFile+"DD"+file.getOriginalFilename();
			
			file.transferTo(new File(uploadPath+"/"+resultFileName));
			
			newMessage.setFileName(resultFileName);
		}
		messageRepo.save(newMessage);
		
		return "redirect:messages";
		
	}
	
	@GetMapping("/filter")
	public String filter(ModelMap model,  @RequestParam(required = false, defaultValue = "") String filter) {
		List<Message> list;
		if(!filter.isEmpty()) {
			list=messageRepo.findByTag(filter);
		}else {
			list=messageRepo.findAll();
			  }
		model.put("messages", list);
		model.put("filter",filter);
		
		return "messages";
		
	}


}
