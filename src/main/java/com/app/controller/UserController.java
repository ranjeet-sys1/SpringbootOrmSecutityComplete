package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.model.User;
import com.app.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService service; //HAS-A
	
	/**
	 * 1. On Enter /register URL
	 *    Display UserRegister.html
	 *    Page in browser
	 */
	@GetMapping("/create")
	public String showReg(Model model) {
		model.addAttribute("user", new User());
		return "UserRegister";
	}
	
	/**
	 * 2. On click Submit(Register),
	 *    Read Form (ModelAttribute)
	 *    Save in Database using Service
	 *    Return Message to UI
	 *    URL :  /save + POST     
	 */
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user,
			Model model) 
	{
		Integer id = service.saveUser(user);
		String message = "User '"+id+"' saved";
		model.addAttribute("message", message);
		return "UserRegister";
	}
	
}

