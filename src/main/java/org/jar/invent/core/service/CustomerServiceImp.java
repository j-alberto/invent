package org.jar.invent.core.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.jar.invent.business.CustomerRegisterBR;
import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.domain.CustomerEntity;
import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.converter.ListConverter;
import org.jar.invent.util.ImageUtils;
import org.jar.invent.web.domain.Customer;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	private CustomerRegisterBR customerRegister;
	@Autowired
	private	ConversionService conversionService;
	@Autowired
	private	ListConverter listConverter;
	
	public void setCustomerRegister(CustomerRegisterBR customerRegister) {
		this.customerRegister = customerRegister;
	}
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	public void setListConverter(ListConverter listConverter) {
		this.listConverter = listConverter;
	}
	
	@Override
	public Customer registerNewCustomer(Customer customer) {
		CustomerEntity customerEnt = conversionService.convert(customer, CustomerEntity.class);
		customerRegister.registerNewCustomer(customerEnt);
		
		return conversionService.convert(customerEnt, Customer.class);
	}
	
	@Override
	public boolean deleteCustomer(Customer customer) {
		CustomerEntity customerEnt = conversionService.convert(customer, CustomerEntity.class);
		
		return customerRegister.deleteCustomer(customerEnt);
	}

	@Override
	public boolean modifyCustomer(Customer customer) {
		CustomerEntity customerEnt = conversionService.convert(customer, CustomerEntity.class);
		
		return customerRegister.deleteCustomer(customerEnt);
	}

	@Override
	public Page<Customer> getCustomers(String code, Pageable pageRequest) {
		Page<CustomerEntity> customersEntPage = customerRegister.getCustomers(code, pageRequest);
		List<Customer> customers = listConverter.convert(customersEntPage.getContent(),Customer.class);
		
		Page<Customer> customersPage = new PageImpl<Customer>(customers,pageRequest,customers.size());
		
		return customersPage;
	}
	@Override
	public Customer getCustomer(int id) {
		return conversionService.convert(customerRegister.getCustomer(id), Customer.class);
	}
	
	
}
