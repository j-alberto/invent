package org.jar.invent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/greeting")
	public String returnGreeting(){
		return "general/GreetingPage";
	}	
	
//	public String returnSecureGreeting(@RequestParam(value="name", required=false,defaultValue="Stranger") String name
	@RequestMapping("/sgreeting")
	public String returnSecureGreeting(){
		
		return "general/SecureGreetingPage";
	}


}
