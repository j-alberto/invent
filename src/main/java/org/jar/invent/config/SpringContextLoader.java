package org.jar.invent.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class SpringContextLoader extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	application.sources(SecurityConfig.class
    						,PersistanceJPAConfig.class
    						,SpringApplicationContext.class);
       return application
    		   .logStartupInfo(true)
    		   .web(true)
    		   .showBanner(false);
    }

}