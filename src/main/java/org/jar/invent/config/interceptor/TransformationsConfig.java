package org.jar.invent.config.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jar.invent.web.domain.formatter.WorkFlowFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * Defines formatting and conversion methods.
 * @return
 */
@Configuration
public class TransformationsConfig {

	/**
	 * Registers formatters for Views and Controllers 
	 * @return a factory bean with all formatters available
	 */
    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(){
    	FormattingConversionServiceFactoryBean formatter = new FormattingConversionServiceFactoryBean();
    	@SuppressWarnings("rawtypes")
		Set<Formatter> formatters = new HashSet<>();

		formatters.add(workFlowFormatter());

    	return formatter;
    }
    
    @Bean
    public WorkFlowFormatter workFlowFormatter(){
    	return new WorkFlowFormatter();
    }
}
