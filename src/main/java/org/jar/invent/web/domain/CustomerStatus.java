package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jar.invent.core.domain.EnumStatusGeneral;

public class CustomerStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	@NotBlank
	@Length(min=1, max=30)
	private String description;
	private EnumStatusGeneral status;
	private List<Customer> customers;

		public CustomerStatus() {
			this.status = EnumStatusGeneral.REGISTERED;
		}
		
		public CustomerStatus(int id, String description, EnumStatusGeneral status) {
			this.id = id;
			this.description = description;
			this.status = status;
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

	@Override
	public String toString(){
		return String.format("CustomerStatus{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}
}