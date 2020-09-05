package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//emp     ---> EmployeePage.html  
	@GetMapping("/emp")
	public String showEmp() {
		return "EmployeePage";
	}
	//std     ---> StudentPage.html
	@GetMapping("/std")
	public String showStd() {
		return "StudentPage";
	}
	//adm     ---> AdminPage.html  
	@GetMapping("/adm")
	public String showAdmin() {
		return "AdminPage";
	}
	//common  ---> CommonPage.html 
	@GetMapping("/common")
	public String showCommon() {
		return "CommonPage";
	}
	//welcome ---> WelcomePage.html
	@GetMapping("/welcome")
	public String showWelcome() {
		return "WelcomePage";
	}
	//denied  ---> AccessDenied.html
	@GetMapping("/denied")
	public String showDenied() {
		return "DeniedPage";
	}

}
