package org.jar.invent.core.service;

import org.jar.invent.web.domain.Customer;
import org.jar.invent.web.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

	public Customer registerNewCustomer(Customer customer);
	public boolean deleteCustomer(Customer customer);
	public boolean modifyCustomer(Customer customer);
	Page<Customer> getCustomers(String code, Pageable pageRequest);
	public Customer getCustomer(int id);
	
}
