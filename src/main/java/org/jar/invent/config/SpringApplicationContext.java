package org.jar.invent.config;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jar.invent.web.domain.formatter.WorkFlowFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configuration
@ComponentScan(basePackages={"org.jar.invent.web.controller",
							 "org.jar.invent.core.service",
							 "org.jar.invent.web.service",
							 "org.jar.invent.config"})
@EnableAutoConfiguration
public class SpringApplicationContext {

    public static void main(String[] args) {
    	SpringApplication sa = new SpringApplication(SpringApplicationContext.class, args);
    	sa.run(args);
    }

}