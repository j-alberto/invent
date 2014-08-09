package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerType implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	@NotEmpty
	@Length(min=1, max=30)
	private String description;
	private List<Customer> customers;

		public CustomerType() {
		}
		public CustomerType(int id, String description) {
			this.id = id;
			this.description = description;
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

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setCustomerType(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setCustomerType(null);

		return customer;
	}

	@Override
	public String toString() {
		return String.format("CategoryEntity{id=%d, desc=%s}", id, description);
	}

}