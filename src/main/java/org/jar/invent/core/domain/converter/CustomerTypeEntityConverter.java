package org.jar.invent.core.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.web.domain.CustomerType;

public class CustomerTypeEntityConverter implements Converter<CustomerType, CustomerTypeEntity>{

	@Override
	public CustomerTypeEntity convert(CustomerType bean) {
		if(null == bean) return null;
		
		return new CustomerTypeEntity(bean.getId(), bean.getDescription());
	}

}
