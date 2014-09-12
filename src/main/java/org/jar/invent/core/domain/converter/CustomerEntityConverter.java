package org.jar.invent.core.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerEntity;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.web.domain.Customer;
import org.jar.invent.web.domain.converter.CustomerTypeConverter;

public class CustomerEntityConverter implements Converter<Customer, CustomerEntity>{

	@Autowired
	private CustomerStatusEntityConverter  customerStatusEntityConverter;
	@Autowired
	private CustomerTypeEntityConverter customerTypeEntityConverter;

	@Override
	public CustomerEntity convert(Customer bean) {
		if(null == bean) return null;
		
		CustomerStatusEntity custStatusEnt = customerStatusEntityConverter.convert(bean.getCustomerStatus());
		CustomerTypeEntity custTypeEnt = customerTypeEntityConverter.convert(bean.getCustomerType());
		
		return new CustomerEntity(bean.getId(), bean.getCode(), bean.getDescription(), bean.getName()
								  ,custStatusEnt, custTypeEnt);
	}

	public void setCustomerStatusEntityConverter(
			CustomerStatusEntityConverter customerStatusEntityConverter) {
		this.customerStatusEntityConverter = customerStatusEntityConverter;
	}

	public void setCustomerTypeEntityConverter(
			CustomerTypeEntityConverter customerTypeEntityConverter) {
		this.customerTypeEntityConverter = customerTypeEntityConverter;
	}

}
