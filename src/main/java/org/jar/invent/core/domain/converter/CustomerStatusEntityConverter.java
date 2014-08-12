package org.jar.invent.core.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.web.domain.CustomerStatus;

public class CustomerStatusEntityConverter implements Converter<CustomerStatus, CustomerStatusEntity>{

	@Override
	public CustomerStatusEntity convert(CustomerStatus bean) {
		if(null == bean) return null;
		
		return new CustomerStatusEntity(bean.getId(), bean.getDescription(),bean.getStatus());
	}

}
