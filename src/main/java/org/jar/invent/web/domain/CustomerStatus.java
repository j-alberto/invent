package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import org.jar.invent.core.domain.EnumStatusGeneral;

public class CustomerStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String description;
	private EnumStatusGeneral status;
	private List<Customer> customers;

	public CustomerStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EnumStatusGeneral getStatus() {
		return this.status;
	}

	public void setStatus(EnumStatusGeneral status) {
		this.status = status;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setCustomerStatus(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setCustomerStatus(null);

		return customer;
	}

}