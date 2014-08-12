package org.jar.invent.web.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.web.domain.CustomerStatus;

public class CustomerStatusConverter implements Converter<CustomerStatusEntity, CustomerStatus>{

	@Override
	public CustomerStatus convert(CustomerStatusEntity bean) {
		if(null == bean) return null;
		
		return new CustomerStatus(bean.getId(), bean.getDescription(),bean.getStatus());
	}

}
