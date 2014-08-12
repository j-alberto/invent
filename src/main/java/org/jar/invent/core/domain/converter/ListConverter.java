package org.jar.invent.core.domain.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

public class ListConverter {
	
		public ListConverter() { }
	
	@Autowired
	private	ConversionService conversionService;
	
	public final <T,S>List<T> convert(List<S> beanList, Class<T> clazz){
		if(null == beanList) return null;
			
		List<T> convertedList = new ArrayList<>();
		for(S s : beanList){
			convertedList.add(conversionService.convert(s, clazz));
		}
		return convertedList;
	}
	
	public void setConversionService( ConversionService conversionService){
		this.conversionService = conversionService;
	}

}
