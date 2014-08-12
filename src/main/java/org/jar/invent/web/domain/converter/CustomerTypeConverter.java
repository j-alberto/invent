package org.jar.invent.web.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.web.domain.CustomerType;

public class CustomerTypeConverter implements Converter<CustomerTypeEntity, CustomerType>{

	@Override
	public CustomerType convert(CustomerTypeEntity bean) {
		if(null == bean) return null;
		
		return new CustomerType(bean.getId(), bean.getDescription());
	}

}
