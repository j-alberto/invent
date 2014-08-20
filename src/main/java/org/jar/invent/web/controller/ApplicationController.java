package org.jar.invent.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class ApplicationController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	@RequestMapping("/gallery/**")
	public void getImages(HttpServletResponse response, HttpServletRequest request){
		String imagePath = request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE )
				.toString()
				.replaceFirst("gallery/", "");
				
		byte[] byteBuffer = new byte[1024];
		FileInputStream fis;
		try {
			Path path = FileSystems.getDefault().getPath(imagePath);
			fis = new FileInputStream(path.toFile());
			int length = fis.read(byteBuffer);
			
			response.setContentType("image/*");
			
			while ((fis != null) && ((length = fis.read(byteBuffer)) != -1)){
				response.getOutputStream().write(byteBuffer, 0, length);
			}
			
			//response.setHeader("", value);
			
			fis.close();
			response.getOutputStream().close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}

//		
	}


}
