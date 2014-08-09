package org.jar.invent.core.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the customer_type database table.
 * 
 */
@Entity
@Table(name="customer_type")
public class CustomerTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

		public CustomerTypeEntity() {
		}
		public CustomerTypeEntity(int id, String description) {
			this.id = id;
			this.description = description;
		}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String description;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="customerType")
	private List<CustomerEntity> customers;

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

	public List<CustomerEntity> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public CustomerEntity addCustomer(CustomerEntity customer) {
		getCustomers().add(customer);
		customer.setCustomerType(this);

		return customer;
	}

	public CustomerEntity removeCustomer(CustomerEntity customer) {
		getCustomers().remove(customer);
		customer.setCustomerType(null);

		return customer;
	}
	
	@Override
	public String toString() {
		return String.format("CategoryEntity{id=%d, desc=%s}", id, description);
	}

}