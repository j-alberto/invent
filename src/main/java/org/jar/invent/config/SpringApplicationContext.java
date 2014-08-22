package org.jar.invent.config;
import org.jar.invent.util.ImageUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"org.jar.invent.web.controller",
							 "org.jar.invent.core.service",
							 "org.jar.invent.web.service",
							 "org.jar.invent.business",
							 "org.jar.invent.config"
							 })
@EnableAutoConfiguration
public class SpringApplicationContext {

    public static void main(String[] args) {
    	SpringApplication sa = new SpringApplication(SpringApplicationContext.class, args);
    	sa.run(args);
    }
    
    
    @Bean ImageUtils imageUtils(){
    	return new ImageUtils();
    }
}