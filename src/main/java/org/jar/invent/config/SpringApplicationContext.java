package org.jar.invent.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="org.pke.liberalbus.controller")
@EnableAutoConfiguration
public class SpringApplicationContext {

    public static void main(String[] args) {
    	SpringApplication sa = new SpringApplication(SpringApplicationContext.class, args);
    	sa.run(args);
    }

}