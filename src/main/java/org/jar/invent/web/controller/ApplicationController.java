package org.jar.invent.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	/**
	 * This is meant to be the only access point for images uploaded to application
	 */
	@RequestMapping(value={"/gallery/**/*.jpg","/gallery/**/*.jpeg","/gallery/**/*.png","/gallery/**/*.gif"}
			,produces={MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}
			,method=RequestMethod.GET)	
	public void getImages(HttpServletResponse response, HttpServletRequest request){
		String imagePath = request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE ).toString();
		
		try(FileInputStream fis = readFileAsStream(imagePath);
				ServletOutputStream out = response.getOutputStream()){
			
			setContentType(response, imagePath);
			setContent(out, fis, imagePath);

		} catch (IOException e) {
			log.info("image not found at: "+imagePath);
		}
	}
	
	private FileInputStream readFileAsStream(String url) throws FileNotFoundException{
		Path path = FileSystems.getDefault().getPath( url.replaceFirst("/gallery/", "") );
		return new FileInputStream(path.toFile());
	}
	
	private void setContent(ServletOutputStream out, FileInputStream fis, String url) throws IOException{
		int length;
		byte[] buffer = new byte[4096];

		while ((length = fis.read(buffer)) != -1)
			out.write(buffer,0,length);

	}

	private void setContentType(HttpServletResponse response, String url){
		url = url.toLowerCase();
		if(url.endsWith(".jpg") || url.endsWith(".jpeg")){
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
		}else if(url.endsWith(".png")){
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			
		}else if(url.endsWith(".gif")){
			response.setContentType(MediaType.IMAGE_GIF_VALUE);
			
		}

	}
}
