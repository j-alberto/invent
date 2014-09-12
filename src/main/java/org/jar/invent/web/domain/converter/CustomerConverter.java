package org.jar.invent.web.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.CustomerEntity;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.web.domain.Customer;
import org.jar.invent.web.domain.CustomerStatus;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.converter.CustomerTypeConverter;

public class CustomerConverter implements Converter<CustomerEntity, Customer>{

	@Autowired
	private CustomerStatusConverter  customerStatusConverter;
	@Autowired
	private CustomerTypeConverter customerTypeConverter;

	@Override
	public Customer convert(CustomerEntity bean) {
		if(null == bean) return null;
		
		CustomerStatus custStatus = customerStatusConverter.convert(bean.getCustomerStatus());
		CustomerType custType = customerTypeConverter.convert(bean.getCustomerType());
		
		return new Customer(bean.getId(), bean.getCode(), bean.getDescription(), bean.getName()
								  ,custStatus, custType);
	}

	public void setCustomerStatusConverter(
			CustomerStatusConverter customerStatusConverter) {
		this.customerStatusConverter = customerStatusConverter;
	}

	public void setCustomerTypeConverter(CustomerTypeConverter customerTypeConverter) {
		this.customerTypeConverter = customerTypeConverter;
	}

}
