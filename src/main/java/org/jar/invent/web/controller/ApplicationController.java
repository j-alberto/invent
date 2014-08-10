package org.jar.invent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

	@RequestMapping("/login")
	public String login(){
		return "other/login";
	}
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}

	@RequestMapping("/catalogs")
	public String catalogsSiteMap(){
		return "catalogs/catalogs";
	}
	
	@RequestMapping("/about")
	public String about(){
		return "other/about";
	}

	@RequestMapping("/offsession")
	public String returnGreeting(){
		return "other/GreetingPage";
	}	

	@RequestMapping("/insession")
	public String returnSecureGreeting(){
		
		return "other/SecureGreetingPage";
	}


}
