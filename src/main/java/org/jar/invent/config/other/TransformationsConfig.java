package org.jar.invent.config.other;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jar.invent.core.domain.converter.WorkflowEntityConverter;
import org.jar.invent.web.domain.converter.WorkflowConverter;
import org.jar.invent.web.domain.formatter.WorkFlowFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * Defines formatting and conversion methods.
 * @return
 */
//@Configuration
public class TransformationsConfig {

	/**
	 * Registers formatters and converters for Views and Controllers 
	 * @return a factory bean with  formatters/convertes available
	 */
//    @Bean
//    public FormattingConversionServiceFactoryBean conversionService(){
//    	Logger.getLogger(getClass()).info("creating converter!!!");
//    	
//    	@SuppressWarnings("rawtypes")
//    	Set<Formatter> formatters = new HashSet<>();
//
//		formatters.add(new WorkFlowFormatter());
//
//		FormattingConversionServiceFactoryBean formatter = new FormattingConversionServiceFactoryBean();
//		formatter.setFormatters(formatters);
//		
//    	return formatter;
//    }
//    
//    @Bean
//    public ConversionServiceFactoryBean conversionServiceFactoryBean(){
//		
//		@SuppressWarnings("rawtypes")
//		Set<Converter> converters = new HashSet<>();
//		
//		converters.add(new WorkflowConverter());
//		converters.add(new WorkflowEntityConverter());
//
//		ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
//		factoryBean.setConverters(converters);
//		
//    	return factoryBean;
//    }

   
}
